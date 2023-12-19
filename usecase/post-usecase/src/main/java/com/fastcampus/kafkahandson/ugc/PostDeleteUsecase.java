package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.Data;

public interface PostDeleteUsecase {

    Post delete(Request request);

    @Data
    class Request {
        private final Long postId;
    }
}
