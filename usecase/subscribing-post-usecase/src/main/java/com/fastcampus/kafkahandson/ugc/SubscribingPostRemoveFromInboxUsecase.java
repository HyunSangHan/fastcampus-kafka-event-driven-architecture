package com.fastcampus.kafkahandson.ugc;

public interface SubscribingPostRemoveFromInboxUsecase {

    void deleteSubscribingInboxPost(Long postId);
}
