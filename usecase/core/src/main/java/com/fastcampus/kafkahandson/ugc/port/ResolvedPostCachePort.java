package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

public interface ResolvedPostCachePort {

    void set(ResolvedPost resolvedPost);
    ResolvedPost get(Long postId);
}
