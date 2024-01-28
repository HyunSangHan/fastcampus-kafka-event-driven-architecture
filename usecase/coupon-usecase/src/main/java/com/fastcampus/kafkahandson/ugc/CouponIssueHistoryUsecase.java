package com.fastcampus.kafkahandson.ugc;

public interface CouponIssueHistoryUsecase {

    boolean isFirstRequestFromUser(Long couponEventId, Long userId);
    boolean hasRemainingCoupon(Long couponEventId);
}
