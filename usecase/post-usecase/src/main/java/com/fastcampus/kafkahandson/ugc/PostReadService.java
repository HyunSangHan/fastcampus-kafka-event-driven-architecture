package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import org.springframework.stereotype.Service;

@Service
public class PostReadService implements PostReadUsecase {

    @Override
    public ResolvedPost getById(Long id) {
        return null;
    }
}
