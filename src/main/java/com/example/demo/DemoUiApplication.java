package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;


@EnableCircuitBreaker
@SpringBootApplication
public class DemoUiApplication extends AbstractHttpSessionApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoUiApplication.class, args);
    }



}

