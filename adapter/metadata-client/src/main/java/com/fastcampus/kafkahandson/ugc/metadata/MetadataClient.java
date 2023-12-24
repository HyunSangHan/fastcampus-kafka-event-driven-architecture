package com.fastcampus.kafkahandson.ugc.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MetadataClient {

    private final WebClient metadataWebClient;

    public CategoryResponse getCategoryById(
        Long categoryId
    ) {
        return metadataWebClient
            .get()
            .uri("/categories/" + categoryId)
            .retrieve()
            .bodyToMono(CategoryResponse.class)
            .block();
    }

    public UserResponse getUserById(
        Long userId
    ) {
        return metadataWebClient
            .get()
            .uri("/users/" + userId)
            .retrieve()
            .bodyToMono(UserResponse.class)
            .block();
    }

    public List<Long> getFollowerIdsByUserId(Long userId) {
        return metadataWebClient
            .get()
            .uri("/followers?followingId=" + userId)
            .retrieve()
            .bodyToFlux(Long.class)
            .collectList()
            .block();
    }

    @Data
    @NoArgsConstructor
    public static class CategoryResponse {
        private Long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class UserResponse {
        @JsonProperty
        private Long id;
        @JsonProperty
        private String email;
        @JsonProperty
        private String name;
    }
}