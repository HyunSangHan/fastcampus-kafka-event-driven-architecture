package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.Records;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "User 전체 목록 조회", description = "유저 전체 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Records.User>> list() {
        List<Records.User> users = Records.userMap.values().stream().toList();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "User 단건 조회", description = "userId를 통해 특정 유저 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<Records.User> get(
        @PathVariable(value = "userId") Long id
    ) {
        Records.User user = Records.userMap.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }
}

