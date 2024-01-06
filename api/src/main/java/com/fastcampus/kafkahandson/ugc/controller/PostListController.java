package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.model.PostInListDto;
import com.fastcampus.kafkahandson.ugc.PostSearchUsecase;
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
    private final PostSearchUsecase postSearchUsecase;

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
        @RequestParam("keyword") String keyword,
        @RequestParam("page") int page
    ) {
        List<ResolvedPost> searchedPosts = postSearchUsecase.getSearchResultByKeyword(keyword, page);
        return ResponseEntity.ok().body(searchedPosts.stream().map(this::toDto).toList());
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
