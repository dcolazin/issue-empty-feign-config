package com.github.dcolazin.emptyfeignconfig.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;

public class RetryFeignConfig extends CommonFeignConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, 500, 5);
    }
}
