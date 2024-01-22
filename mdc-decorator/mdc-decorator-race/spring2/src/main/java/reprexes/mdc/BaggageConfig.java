package reprexes.mdc;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaggageConfig {

    public static final String CLASS_BAGGAGE = "classBaggage";
    public static final String METHOD_BAGGAGE = "methodBaggage";
    public static final String BUSINESS_BAGGAGE = "businessBaggage";

    @Bean
    public MyBaggageService baggageService(ApplicationContext ctx) {
        return new MyBaggageService(ctx);
    }

    private BaggageField findOrCreate(String name) {
        BaggageField field = BaggageField.getByName(name);
        if (field == null) {
            field = BaggageField.create(name);
        }
        return field;
    }

    @Bean(CLASS_BAGGAGE)
    BaggageField classField() {
        return findOrCreate(CLASS_BAGGAGE);
    }

    @Bean(METHOD_BAGGAGE)
    BaggageField methodField() {
        return findOrCreate(METHOD_BAGGAGE);
    }

    @Bean(BUSINESS_BAGGAGE)
    BaggageField businessField() {
        return findOrCreate(BUSINESS_BAGGAGE);
    }

    @Bean
    CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
        return MDCScopeDecorator.newBuilder()
            .clear()
            .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(classField())
                .flushOnUpdate()
                .build())
            .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(methodField())
                .flushOnUpdate()
                .build())
            .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(businessField())
                .flushOnUpdate()
                .build())
            .build();
    }

}
