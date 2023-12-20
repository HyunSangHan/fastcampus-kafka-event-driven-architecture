package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.port.PostPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostDeleteService implements PostDeleteUsecase {

    private final PostPort postPort;

    @Transactional
    @Override
    public Post delete(PostDeleteUsecase.Request request) {
        Post post = postPort.findById(request.getPostId());
        if (post == null) return null;
        post.delete();
        return postPort.save(post);
    }
}
