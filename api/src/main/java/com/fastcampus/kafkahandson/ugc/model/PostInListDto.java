package com.fastcampus.kafkahandson.ugc.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostInListDto { // 목록에 노출하기 위한 용도의 요약버전

    private final Long id;
    private final String title;
    private final String userName;
    private final LocalDateTime createdAt;
}