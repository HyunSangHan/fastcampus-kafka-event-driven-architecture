package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.Records;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FollowController {

    @Operation(summary = "구독관계 전체 목록 조회", description = "구독관계 전체 목록을 조회합니다.")
    @GetMapping("/follows")
    public ResponseEntity<List<Records.Follow>> listAll() {
        return ResponseEntity.ok().body(Records.allFollows);
    }

    @Operation(summary = "구독채널 목록 조회", description = "특정 follower(= followerId)가 구독하고 있는 채널 전체 목록을 조회합니다.")
    @GetMapping("/followings")
    public ResponseEntity<List<Long>> listByFollowerId(
        @RequestParam(name = "followerId") Long followerId
    ) {
        List<Long> followingIds = Records.followingIdsByFollowerIdMap.get(followerId);
        if (followingIds == null) {
            return ResponseEntity.ok().body(List.of());
        }
        return ResponseEntity.ok().body(followingIds.stream().sorted().toList());
    }

    @Operation(summary = "구독자 목록 조회", description = "특정 채널(= followingId)을 구독하고 있는 구독자 전체 목록을 조회합니다.")
    @GetMapping("/followers")
    public ResponseEntity<List<Long>> listByFollowingId(
        @RequestParam(name = "followingId") Long followingId
    ) {
        List<Long> followerIds = Records.followerIdsByFollowingIdMap.get(followingId);
        if (followerIds == null) {
            return ResponseEntity.ok().body(List.of());
        }
        if (Math.random() < 0.2) { // 20% 확률로 1초 지연 후 500 응답
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(followerIds.stream().sorted().toList());
    }
}

