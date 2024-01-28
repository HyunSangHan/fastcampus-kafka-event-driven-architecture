package com.fastcampus.kafkahandson.ugc.coupon;

import com.fastcampus.kafkahandson.ugc.coupon.model.Coupon;
import com.fastcampus.kafkahandson.ugc.coupon.model.CouponEvent;

public class CouponEntityConverter {

    public static CouponEntity toCouponEntity(Coupon coupon) {
        return new CouponEntity(
            coupon.getId(),
            coupon.getUserId(),
            coupon.getCouponEventId(),
            coupon.getIssuedAt(),
            coupon.getUsedAt()
        );
    }

    public static Coupon toCouponModel(CouponEntity couponEntity) {
        return new Coupon(
            couponEntity.getId(),
            couponEntity.getUserId(),
            couponEntity.getCouponEventId(),
            couponEntity.getIssuedAt(),
            couponEntity.getUsedAt()
        );
    }

    public static CouponEvent toCouponEventModel(CouponEventEntity couponEventEntity) {
        return new CouponEvent(
            couponEventEntity.getId(),
            couponEventEntity.getDisplayName(),
            couponEventEntity.getExpiresAt(),
            couponEventEntity.getIssueLimit()
        );
    }
}
