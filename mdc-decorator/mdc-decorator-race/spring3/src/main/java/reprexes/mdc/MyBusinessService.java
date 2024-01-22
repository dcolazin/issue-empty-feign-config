package reprexes.mdc;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ContextSnapshot;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.contextpropagation.ObservationAwareSpanThreadLocalAccessor;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static reprexes.mdc.BaggageConfig.BUSINESS_BAGGAGE;
import static reprexes.mdc.BaggageConfig.CLASS_BAGGAGE;
import static reprexes.mdc.BaggageConfig.METHOD_BAGGAGE;

@Service
@RequiredArgsConstructor
public class MyBusinessService {

    private static final int DEFAULT_PARALLELISM = 8;

    private final Tracer tracer;

    public List<String> businessLogic() {
        List<Integer> inputs = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return returnParallelExecution(
            inputs,
            this::innerBusinessLogic,
            DEFAULT_PARALLELISM);
    }

    public String innerBusinessLogic(Integer i) {
        decorateBaggage(String.valueOf(i));
        String s = Thread.currentThread().getName() + " " + i + " " + MDC.getCopyOfContextMap();
        System.out.println(s);
        return s;
    }

    public <I, O> List<O> returnParallelExecution(List<I> list, Function<I, O> function, Integer parallelism) {
        ContextRegistry.getInstance().registerThreadLocalAccessor(new ObservationAwareSpanThreadLocalAccessor(tracer));
        ExecutorService traceableExecutorService = ContextExecutorService.wrap(
            Executors.newFixedThreadPool(Optional.ofNullable(parallelism).orElse(DEFAULT_PARALLELISM)),
            ContextSnapshot::captureAll);
        try {
            List<CompletableFuture<O>> futures = list.stream()
                .map(i ->
                    CompletableFuture.supplyAsync(() ->
                        function.apply(i), traceableExecutorService))
                .toList();
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
            return futures.stream().map(MyBusinessService::getFutureWrapper).toList();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw  new RuntimeException(e);
        } finally {
            traceableExecutorService.shutdown();
        }
    }

    private static <T> T getFutureWrapper(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private void decorateBaggage(String value) {
        StackWalker walker = StackWalker.getInstance();
        String methodName = walker.walk(frames -> frames.skip(1).findFirst().map(StackWalker.StackFrame::getMethodName)).orElse(null);
        MyBaggageService.updateValue(CLASS_BAGGAGE, this.getClass().getName());
        MyBaggageService.updateValue(METHOD_BAGGAGE, methodName);
        MyBaggageService.updateValue(BUSINESS_BAGGAGE, value);
    }

}
