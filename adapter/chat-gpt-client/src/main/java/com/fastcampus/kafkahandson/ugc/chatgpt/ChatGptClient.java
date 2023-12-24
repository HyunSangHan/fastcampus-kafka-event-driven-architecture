package com.fastcampus.kafkahandson.ugc.chatgpt;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.chatgpt.model.ChatCompletionResponse;
import lombok.Data;
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

    public String getResultForContentWithPolicy(
        String content,
        ChatPolicy chatPolicy
    ) {
        String jsonString = chatGptWebClient
            .post()
            .uri("/v1/chat/completions")
            .header("Authorization", "Bearer " + openaiApiKey) // OpenAI API 키 추가
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(Map.of(
                "model", TARGET_GPT_MODEL,
                "messages", List.of(
                    Map.of("role", "system", "content", chatPolicy.instruction),
                    Map.of("role", "user", "content", chatPolicy.exampleContent),
                    Map.of("role", "assistant", "content", chatPolicy.exampleInspectionResult),
                    Map.of("role", "user", "content", content)
                ),
                "stream", false
            ))
            .retrieve()
            .bodyToMono(String.class)
            .block();
        try {
            ChatCompletionResponse response = objectMapper.readValue(jsonString, ChatCompletionResponse.class);
            return response.getChoices()[0].getMessage().getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class ChatPolicy {
        private final String instruction;
        private final String exampleContent;
        private final String exampleInspectionResult;
    }
}