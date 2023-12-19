package com.fastcampus.kafkahandson.ugc.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post { // 원천 관리용

    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Post update(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public Post delete() {
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        this.deletedAt = now;
        return this;
    }

    public Post undelete() {
        this.deletedAt = null;
        return this;
    }

    public static Post generate(
        Long userId,
        String title,
        String content,
        Long categoryId
    ) {
        LocalDateTime now = LocalDateTime.now();
        return new Post(null, title, content, userId, categoryId, now, now, null);
    }
}