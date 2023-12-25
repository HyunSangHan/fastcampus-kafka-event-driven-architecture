package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.model.PostInListDto;
import com.fastcampus.kafkahandson.ugc.SubscribingPostListUsecase;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/list")
public class PostListController {

    private final SubscribingPostListUsecase subscribingPostListUsecase;

    @GetMapping("/inbox/{userId}") // 실제로는 이렇게 안하겠지만..
    ResponseEntity<List<PostInListDto>> listSubscribingInboxPosts(
        @PathVariable("userId") Long userId,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page
    ) {
        List<ResolvedPost> subscribingInboxPosts = subscribingPostListUsecase.listSubscribingInboxPosts(
            new SubscribingPostListUsecase.Request(page, userId)
        );
        return ResponseEntity.ok().body(subscribingInboxPosts.stream().map(this::toDto).toList());
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
