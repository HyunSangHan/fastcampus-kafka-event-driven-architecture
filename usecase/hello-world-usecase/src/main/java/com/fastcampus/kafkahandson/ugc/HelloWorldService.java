package com.fastcampus.kafkahandson.ugc;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements HelloWorldUsecase {

    @Override
    public String getHelloWorldString() {
        return "Hello World!";
    }
}
