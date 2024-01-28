package com.fastcampus.kafkahandson.ugc.adapter.couponissuerequest;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.port.CouponIssueRequestPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.fastcampus.kafkahandson.ugc.adapter.common.Topic.COUPON_ISSUE_REQUEST;

@RequiredArgsConstructor
@Component
public class CouponIssueRequestAdapter implements CouponIssueRequestPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendMessage(Long userId, Long couponEventId) {
        CouponIssueRequestMessage message = CouponIssueRequestMessage.from(userId, couponEventId);
        try {
            kafkaTemplate.send(COUPON_ISSUE_REQUEST, message.getUserId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
