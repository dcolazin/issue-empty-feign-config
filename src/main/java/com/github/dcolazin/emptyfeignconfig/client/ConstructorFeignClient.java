package com.github.dcolazin.emptyfeignconfig.client;

import com.github.dcolazin.emptyfeignconfig.config.ConstructorFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "constructorFeignClient", url = "http://localhost:8080", configuration = ConstructorFeignConfig.class)
public interface ConstructorFeignClient {

    @GetMapping
    String homepage();

}

