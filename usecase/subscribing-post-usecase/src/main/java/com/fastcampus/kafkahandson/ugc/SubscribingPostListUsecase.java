package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.Data;

import java.util.List;

public interface SubscribingPostListUsecase {

    List<ResolvedPost> listSubscribingInboxPosts(Request request);

    @Data
    class Request {
        private final int pageNumber;
        private final Long followerUserId;
    }
}
