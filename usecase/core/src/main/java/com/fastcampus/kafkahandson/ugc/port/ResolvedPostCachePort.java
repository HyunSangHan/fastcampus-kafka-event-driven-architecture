package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface ResolvedPostCachePort {

    void set(ResolvedPost resolvedPost);
    ResolvedPost get(Long postId);
    List<ResolvedPost> multiGet(List<Long> postIds);
    void delete(Long postId);
}
