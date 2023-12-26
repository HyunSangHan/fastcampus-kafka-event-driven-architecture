package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface PostResolvingHelpUsecase {

    ResolvedPost resolvePostById(Long postId);
    List<ResolvedPost> resolvePostsByIds(List<Long> postIds);
    void resolvePostAndSave(Post post);
    void deleteResolvedPost(Long postId);
}
