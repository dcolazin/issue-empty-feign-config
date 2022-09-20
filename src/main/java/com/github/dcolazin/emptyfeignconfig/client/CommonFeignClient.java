package com.github.dcolazin.emptyfeignconfig.client;

import com.github.dcolazin.emptyfeignconfig.config.CommonFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "commonFeignClient", url = "http://localhost:8080", configuration = CommonFeignConfig.class)
public interface CommonFeignClient {

    @GetMapping
    String homepage();

}
