package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.coupon.model.CouponEvent;
import com.fastcampus.kafkahandson.ugc.port.CouponEventCachePort;
import com.fastcampus.kafkahandson.ugc.port.CouponEventPort;
import com.fastcampus.kafkahandson.ugc.port.CouponIssueRequestHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponIssueHistoryService implements CouponIssueHistoryUsecase {

    private final CouponIssueRequestHistoryPort couponIssueRequestHistoryPort;
    private final CouponEventPort couponEventPort;
    private final CouponEventCachePort couponEventCachePort;

    @Override
    public boolean isFirstRequestFromUser(Long couponEventId, Long userId) {
        return couponIssueRequestHistoryPort.setHistoryIfNotExists(couponEventId, userId);
    }

    @Override
    public boolean hasRemainingCoupon(Long couponEventId) {
        CouponEvent couponEvent = this.getCouponEventById(couponEventId);
        return couponIssueRequestHistoryPort.getRequestSequentialNumber(couponEventId) <= couponEvent.getIssueLimit();
    }

    private CouponEvent getCouponEventById(Long couponEventId) {
        CouponEvent couponEventCache = couponEventCachePort.get(couponEventId);
        if (couponEventCache != null) {
            return couponEventCache;
        } else {
            CouponEvent couponEvent = couponEventPort.findById(couponEventId);
            if (couponEvent == null) {
                throw new RuntimeException("Coupon event does not exist.");
            }
            couponEventCachePort.set(couponEvent);
            return couponEvent;
        }
    }
}
