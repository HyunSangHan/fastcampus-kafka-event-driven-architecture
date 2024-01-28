package com.fastcampus.kafkahandson.ugc.adapter.couponissuerequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponIssueRequestMessage {
    private Long userId;
    private Long couponEventId;

    public static CouponIssueRequestMessage from(Long userId, Long couponEventId) {
        return new CouponIssueRequestMessage(userId, couponEventId);
    }
}
