package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.post.model.Post;

import java.util.List;

public interface PostPort {

    Post save(Post post);
    Post findById(Long id);
    List<Post> listByIds(List<Long> ids);
}
