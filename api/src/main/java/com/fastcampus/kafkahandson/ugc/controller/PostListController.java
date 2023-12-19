package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.model.PostInListDto;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/list")
public class PostListController {

    @GetMapping("/inbox/{userId}") // 실제로는 이렇게 안하겠지만..
    ResponseEntity<List<PostInListDto>> listSubscribingPosts(
        @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/search")
    ResponseEntity<List<PostInListDto>> searchPosts(
        @RequestParam("query") String query
    ) {
        return ResponseEntity.internalServerError().build();
    }

    private PostInListDto toDto(ResolvedPost resolvedPost) {
        return new PostInListDto(
            resolvedPost.getId(),
            resolvedPost.getTitle(),
            resolvedPost.getUserName(),
            resolvedPost.getCreatedAt()
        );
    }
}
