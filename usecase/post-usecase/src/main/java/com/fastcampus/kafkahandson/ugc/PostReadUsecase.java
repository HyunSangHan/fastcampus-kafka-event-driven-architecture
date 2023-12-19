package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

public interface PostReadUsecase {

    ResolvedPost getById(Long id);
}
