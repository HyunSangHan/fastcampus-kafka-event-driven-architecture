package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.coupon.model.CouponEvent;

public interface CouponEventPort {

    CouponEvent findById(Long id);
}
