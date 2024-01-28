package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.coupon.model.CouponEvent;

public interface CouponEventCachePort {

    void set(CouponEvent couponEvent);
    CouponEvent get(Long couponEventId);
}
