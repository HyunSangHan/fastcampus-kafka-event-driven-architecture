package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fastcampus.kafkahandson.ugc.post.model.Post;

import java.util.List;

public interface PostSearchPort {

    void indexPost(InspectedPost post);
    void deletePost(Long id);
}
