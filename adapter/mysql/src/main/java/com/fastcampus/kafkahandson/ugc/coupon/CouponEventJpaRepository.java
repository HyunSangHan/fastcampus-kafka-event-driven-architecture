package com.fastcampus.kafkahandson.ugc.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponEventJpaRepository extends JpaRepository<CouponEventEntity, Long> { }
