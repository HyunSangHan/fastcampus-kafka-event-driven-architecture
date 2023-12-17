package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.HelloWorldUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloWorldController {

    private final HelloWorldUsecase helloWorldUsecase;

    @GetMapping("/hello")
    public String hello() {
        return helloWorldUsecase.getHelloWorldString();
    }
}
