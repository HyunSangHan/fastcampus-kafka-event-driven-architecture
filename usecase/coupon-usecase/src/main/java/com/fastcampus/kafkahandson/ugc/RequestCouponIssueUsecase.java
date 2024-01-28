package com.fastcampus.kafkahandson.ugc;

public interface RequestCouponIssueUsecase {

    void queue(Long couponEventId, Long userId);
}
