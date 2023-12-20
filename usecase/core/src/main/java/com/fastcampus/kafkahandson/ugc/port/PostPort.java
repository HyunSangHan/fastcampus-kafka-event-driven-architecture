package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.post.model.Post;

public interface PostPort {

    Post save(Post post);
    Post findById(Long id);
}
