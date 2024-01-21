package com.fastcampus.kafkahandson.ugc.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coupon {

    private Long id; // coupon id (동일한 coupon event라 해도, 발급받은 유저마다 coupon id가 다 다름)
    private Long userId; // coupon을 발급받은 user의 id
    private Long couponEventId; // coupon event의 id
    private LocalDateTime issuedAt; // coupon 발급 일시
    private LocalDateTime usedAt; // coupon 사용 일시

    public Coupon use() {
        this.usedAt = LocalDateTime.now();
        return this;
    }

    public static Coupon generate(
        Long userId,
        Long couponEventId
    ) {
        return new Coupon(null, userId, couponEventId, LocalDateTime.now(), null);
    }
}
