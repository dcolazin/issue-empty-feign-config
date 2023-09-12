package com.github.dcolazin.validationbean;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/test")
public class MyController {

    @GetMapping
    public String test(@RequestBody @Valid MyBody request) {
        return "hello world";
    }
}
