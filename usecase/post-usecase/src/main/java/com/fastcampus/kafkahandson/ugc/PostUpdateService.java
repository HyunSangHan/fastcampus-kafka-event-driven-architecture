package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostUpdateService implements PostUpdateUsecase {
    @Override
    public Post update(PostUpdateUsecase.Request request) {
        return null; // TODO
    }
}
