package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostDeleteService implements PostDeleteUsecase {
    @Override
    public Post delete(PostDeleteUsecase.Request request) {
        return null; // TODO
    }
}
