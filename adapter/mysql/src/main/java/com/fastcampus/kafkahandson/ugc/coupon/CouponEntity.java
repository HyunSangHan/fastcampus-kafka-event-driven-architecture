package com.fastcampus.kafkahandson.ugc.coupon;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "coupon")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long couponEventId;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
}
