package com.fastcampus.kafkahandson.ugc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CouponIssueRequest {
    private Long userId;
    private Long couponEventId;
}