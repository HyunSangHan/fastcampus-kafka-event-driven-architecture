package com.fastcampus.kafkahandson.ugc.chatgpt;

import com.fastcampus.kafkahandson.ugc.port.TestChatGptPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestChatGptAdapter implements TestChatGptPort {

    private final ChatGptClient chatGptClient;

    @Override
    public String test(String content) {
        return chatGptClient.testChatGpt(content);
    }
}
