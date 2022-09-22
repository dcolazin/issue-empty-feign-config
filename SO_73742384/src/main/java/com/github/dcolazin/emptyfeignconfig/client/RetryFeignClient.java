package com.github.dcolazin.emptyfeignconfig.client;

import com.github.dcolazin.emptyfeignconfig.config.RetryFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "retryFeignClient", url = "http://localhost:8080", configuration = RetryFeignConfig.class)
public interface RetryFeignClient {

    @GetMapping
    String homepage();

}