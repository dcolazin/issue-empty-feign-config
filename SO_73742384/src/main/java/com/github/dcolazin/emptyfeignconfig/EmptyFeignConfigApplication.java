package com.github.dcolazin.emptyfeignconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.github.dcolazin.emptyfeignconfig.client" )
public class EmptyFeignConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmptyFeignConfigApplication.class, args);
	}

}
