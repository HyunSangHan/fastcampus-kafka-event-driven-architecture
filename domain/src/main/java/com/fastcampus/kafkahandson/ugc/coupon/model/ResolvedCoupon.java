package com.fastcampus.kafkahandson.ugc.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResolvedCoupon {

    private Coupon coupon;
    private CouponEvent couponEvent;

    public boolean canBeUsed() {
        return !this.couponEvent.isExpired() && this.coupon.getUsedAt() == null;
    }

    public static ResolvedCoupon generate(
        Coupon coupon,
        CouponEvent couponEvent
    ) {
        return new ResolvedCoupon(coupon, couponEvent);
    }
}
