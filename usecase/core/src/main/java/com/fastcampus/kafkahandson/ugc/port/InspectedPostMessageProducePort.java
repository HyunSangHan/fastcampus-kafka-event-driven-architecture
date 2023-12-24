package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;

public interface InspectedPostMessageProducePort {

    void sendCreateMessage(InspectedPost inspectedPost);
    void sendUpdateMessage(InspectedPost inspectedPost);
    void sendDeleteMessage(Long id);
}
