package com.fastcampus.kafkahandson.ugc.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> { }
