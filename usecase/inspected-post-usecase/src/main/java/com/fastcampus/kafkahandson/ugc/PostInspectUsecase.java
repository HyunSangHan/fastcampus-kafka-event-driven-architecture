package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fastcampus.kafkahandson.ugc.post.model.Post;

public interface PostInspectUsecase {

    InspectedPost inspectAndGetIfValid(Post post);
}
