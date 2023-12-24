package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.port.TestChatGptPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal")
public class InternalController {

    private final TestChatGptPort testChatGptPort;

    @GetMapping
    String test(
        @RequestParam("content") String content
    ) {
        return testChatGptPort.test(content);
    }
}
