package com.fastcampus.kafkahandson.ugc.chatgpt.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatGptWebClientConfig {

    @Bean
    @Qualifier("chatGptWebClient")
    public WebClient chatGptWebClient() {
        return WebClient.builder()
            .baseUrl("https://api.openai.com")
            .build();
    }
}