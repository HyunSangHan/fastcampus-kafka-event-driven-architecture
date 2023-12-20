package com.fastcampus.kafkahandson.ugc.post;

import com.fastcampus.kafkahandson.ugc.post.model.Post;

public class PostEntityConverter {

    public static PostEntity toEntity(Post post) {
        return new PostEntity(
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

    public static Post toModel(PostEntity postEntity) {
        return new Post(
            postEntity.getId(),
            postEntity.getTitle(),
            postEntity.getContent(),
            postEntity.getUserId(),
            postEntity.getCategoryId(),
            postEntity.getCreatedAt(),
            postEntity.getUpdatedAt(),
            postEntity.getDeletedAt()
        );
    }
}
