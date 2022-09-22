package com.github.dcolazin.emptyfeignconfig.controller;

import com.github.dcolazin.emptyfeignconfig.client.CommonFeignClient;
import com.github.dcolazin.emptyfeignconfig.client.ConstructorFeignClient;
import com.github.dcolazin.emptyfeignconfig.client.EmptyFeignClient;
import com.github.dcolazin.emptyfeignconfig.client.RetryFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CommonFeignClient commonFeignClient;
    private final ConstructorFeignClient constructorFeignClient;
    private final EmptyFeignClient emptyFeignClient;
    private final RetryFeignClient retryFeignClient;

    @GetMapping
    String endpoint() {
        return "OK";
    }

    @GetMapping("/common")
    String common() {
        return commonFeignClient.homepage();
    }

    @GetMapping("/constructor")
    String contructor() {
        return constructorFeignClient.homepage();
    }

    @GetMapping("/empty")
    String empty() {
        return emptyFeignClient.homepage();
    }

    @GetMapping("/retry")
    String retry() {
        return retryFeignClient.homepage();
    }

}
