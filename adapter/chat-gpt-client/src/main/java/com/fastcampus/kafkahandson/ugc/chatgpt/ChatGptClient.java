package com.fastcampus.kafkahandson.ugc.chatgpt;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class ChatGptClient {

    private static final String TARGET_GPT_MODEL = "gpt-3.5-turbo";

    public ChatGptClient(
        @Qualifier("chatGptWebClient") WebClient chatGptWebClient
    ) {
        this.chatGptWebClient = chatGptWebClient;
    }

    private final WebClient chatGptWebClient;

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    public String testChatGpt(String content) {
        return chatGptWebClient
            .post()
            .uri("/v1/chat/completions")
            .header("Authorization", "Bearer " + openaiApiKey) // OpenAI API 키 추가
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(Map.of(
                "model", TARGET_GPT_MODEL,
                "messages", List.of(
                    Map.of("role", "system", "content", "You are an assistant."),
                    Map.of("role", "user", "content", content)
                ),
                "stream", false
            ))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}