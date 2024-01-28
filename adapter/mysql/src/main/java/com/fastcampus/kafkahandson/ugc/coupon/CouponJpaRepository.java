package com.fastcampus.kafkahandson.ugc.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> { }
