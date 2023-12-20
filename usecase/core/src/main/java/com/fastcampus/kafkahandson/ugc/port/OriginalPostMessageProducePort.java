package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.post.model.Post;

public interface OriginalPostMessageProducePort {

    void sendCreateMessage(Post post);
    void sendUpdateMessage(Post post);
    void sendDeleteMessage(Long id);
}
