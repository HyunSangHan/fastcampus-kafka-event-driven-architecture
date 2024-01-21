package com.fastcampus.kafkahandson.ugc.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponEvent {

    private Long id; // coupon event의 id
    private String displayName; // coupon에 대한 노출 이름
    private LocalDateTime expiresAt; // coupon 만료 일시
    private Long issueLimit; // coupon 발급 제한 수

    public boolean isExpired() {
        return this.expiresAt.isBefore(LocalDateTime.now());
    }

    public static CouponEvent generate(
        String displayName,
        LocalDateTime expiresAt,
        Long issueLimit
    ) {
        return new CouponEvent(null, displayName, expiresAt, issueLimit);
    }
}
