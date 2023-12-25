package com.fastcampus.kafkahandson.ugc.subscribingpost;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "subscribingInboxPosts")
@AllArgsConstructor
@Data
public class SubscribingPostDocument {

    @Id
    private String id; // postId와 followerUserId의 조합

    private Long postId;
    private Long followerUserId; // follower(구독자) user id
    private LocalDateTime postCreatedAt; // 컨텐츠의 생성시점
    private LocalDateTime addedAt; // follower 유저의 구독 목록에 반영된 시점
    private boolean read; // 해당 구독 컨텐츠 조회 여부

    public static SubscribingPostDocument generate(
        Post post,
        Long followerUserId
    ) {
        return new SubscribingPostDocument(
            generateDocumentId(post.getId(), followerUserId),
            post.getId(),
            followerUserId,
            post.getCreatedAt(),
            LocalDateTime.now(),
            false
        );
    }

    private static String generateDocumentId(Long postId, Long followerUserId) {
        return postId + "_" + followerUserId;
    }
}
