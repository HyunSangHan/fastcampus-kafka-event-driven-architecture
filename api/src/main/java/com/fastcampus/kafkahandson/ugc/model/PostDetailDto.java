package com.fastcampus.kafkahandson.ugc.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDetailDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String userName;
    private final String categoryName;
    private final LocalDateTime createdAt;
    private final boolean updated;
}