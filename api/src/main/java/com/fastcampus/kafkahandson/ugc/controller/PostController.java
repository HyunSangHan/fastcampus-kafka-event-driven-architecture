package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.PostCreateUsecase;
import com.fastcampus.kafkahandson.ugc.PostDeleteUsecase;
import com.fastcampus.kafkahandson.ugc.PostReadUsecase;
import com.fastcampus.kafkahandson.ugc.PostUpdateUsecase;
import com.fastcampus.kafkahandson.ugc.model.PostCreateRequest;
import com.fastcampus.kafkahandson.ugc.model.PostDetailDto;
import com.fastcampus.kafkahandson.ugc.model.PostDto;
import com.fastcampus.kafkahandson.ugc.model.PostUpdateRequest;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostCreateUsecase postCreateUsecase;
    private final PostUpdateUsecase postUpdateUsecase;
    private final PostDeleteUsecase postDeleteUsecase;
    private final PostReadUsecase postReadUsecase;

    @PostMapping
    ResponseEntity<PostDto> createPost(
        @RequestBody PostCreateRequest request
    ) {
        Post post = postCreateUsecase.create(
            new PostCreateUsecase.Request(
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getCategoryId()
            )
        );
        return ResponseEntity.ok().body(toDto(post));
    }

    @PutMapping("/{postId}")
    ResponseEntity<PostDto> updatePost(
        @PathVariable("postId") Long id,
        @RequestBody PostUpdateRequest request
    ) {
        Post post = postUpdateUsecase.update(
            new PostUpdateUsecase.Request(
                id,
                request.getTitle(),
                request.getContent(),
                request.getCategoryId()
            )
        );
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(toDto(post));
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<PostDto> deletePost(
        @PathVariable("postId") Long id
    ) {
        Post post = postDeleteUsecase.delete(
            new PostDeleteUsecase.Request(
                id
            )
        );
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(toDto(post));
    }

    @GetMapping("/{postId}/detail")
    ResponseEntity<PostDetailDto> readPostDetail(
        @PathVariable("postId") Long id
    ) {
        ResolvedPost resolvedPost = postReadUsecase.getById(id);
        if (resolvedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(toDto(resolvedPost));
    }

    private PostDto toDto(Post post) {
        return new PostDto(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getUserId(),
            post.getCategoryId(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getDeletedAt()
        );
    }

    private PostDetailDto toDto(ResolvedPost resolvedPost) {
        return new PostDetailDto(
            resolvedPost.getId(),
            resolvedPost.getTitle(),
            resolvedPost.getContent(),
            resolvedPost.getUserName(),
            resolvedPost.getCategoryName(),
            resolvedPost.getCreatedAt(),
            resolvedPost.isUpdated()
        );
    }
}
