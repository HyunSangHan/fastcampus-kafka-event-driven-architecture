package com.fastcampus.kafkahandson.ugc.coupon;

import com.fastcampus.kafkahandson.ugc.coupon.model.CouponEvent;
import com.fastcampus.kafkahandson.ugc.port.CouponEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponEventAdapter implements CouponEventPort {

    private final CouponEventJpaRepository couponEventJpaRepository;

    @Override
    public CouponEvent findById(Long id) {
        CouponEventEntity couponEventEntity = couponEventJpaRepository.findById(id).orElse(null);
        if (couponEventEntity == null) {
            return null;
        }
        return CouponEntityConverter.toCouponEventModel(couponEventEntity);
    }
}
