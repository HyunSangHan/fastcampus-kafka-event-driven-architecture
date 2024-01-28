package com.fastcampus.kafkahandson.ugc.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CouponDto {
    private final Long id;
    private final String displayName;
    private final LocalDateTime expiresAt;
}