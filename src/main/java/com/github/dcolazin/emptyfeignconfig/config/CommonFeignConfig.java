package com.github.dcolazin.emptyfeignconfig.config;

import com.github.dcolazin.emptyfeignconfig.interceptor.LogOkHttpInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class CommonFeignConfig {
    @Bean
    public LogOkHttpInterceptor LogOkHttpInterceptor() { //custom interceptor
        return new LogOkHttpInterceptor();
    }

    @Bean
    public feign.okhttp.OkHttpClient okHttpClient(LogOkHttpInterceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return new feign.okhttp.OkHttpClient(builder.build());
    }
}