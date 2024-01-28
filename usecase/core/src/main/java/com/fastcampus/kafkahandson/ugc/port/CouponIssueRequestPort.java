package com.fastcampus.kafkahandson.ugc.port;

public interface CouponIssueRequestPort {

    void sendMessage(Long userId, Long couponEventId);
}
