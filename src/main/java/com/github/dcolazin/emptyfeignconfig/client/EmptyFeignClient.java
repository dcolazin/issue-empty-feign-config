package com.github.dcolazin.emptyfeignconfig.client;

import com.github.dcolazin.emptyfeignconfig.config.EmptyFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "emptyFeignClient", url = "http://localhost:8080", configuration = EmptyFeignConfig.class)
public interface EmptyFeignClient {

    @GetMapping
    String homepage();

}
