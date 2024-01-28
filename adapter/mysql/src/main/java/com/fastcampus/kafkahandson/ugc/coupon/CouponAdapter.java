package com.fastcampus.kafkahandson.ugc.coupon;

import com.fastcampus.kafkahandson.ugc.coupon.model.Coupon;
import com.fastcampus.kafkahandson.ugc.port.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponAdapter implements CouponPort {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        CouponEntity couponEntity = couponJpaRepository.save(toEntity(coupon));
        return toModel(couponEntity);
    }

    private CouponEntity toEntity(Coupon coupon) {
        return new CouponEntity(
            coupon.getId(),
            coupon.getUserId(),
            coupon.getCouponEventId(),
            coupon.getIssuedAt(),
            coupon.getUsedAt()
        );
    }

    private Coupon toModel(CouponEntity couponEntity) {
        return new Coupon(
            couponEntity.getId(),
            couponEntity.getUserId(),
            couponEntity.getCouponEventId(),
            couponEntity.getIssuedAt(),
            couponEntity.getUsedAt()
        );
    }
}
