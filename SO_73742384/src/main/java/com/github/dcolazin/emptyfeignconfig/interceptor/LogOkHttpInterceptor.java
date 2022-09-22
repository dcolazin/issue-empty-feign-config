package com.github.dcolazin.emptyfeignconfig.interceptor;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class LogOkHttpInterceptor implements Interceptor {

    public LogOkHttpInterceptor() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        log.info("Intercepted!");
        Request request = chain.request();
        return chain.proceed(request);
    }
}
