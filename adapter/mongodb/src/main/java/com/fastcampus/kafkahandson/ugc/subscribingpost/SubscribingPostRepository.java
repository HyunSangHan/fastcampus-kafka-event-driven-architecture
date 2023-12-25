package com.fastcampus.kafkahandson.ugc.subscribingpost;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscribingPostRepository extends SubscribingPostCustomRepository, MongoRepository<SubscribingPostDocument, String> {
}
