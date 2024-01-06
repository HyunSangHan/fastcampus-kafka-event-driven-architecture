package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface PostSearchUsecase {

    List<ResolvedPost> getSearchResultByKeyword(String keyword, int pageNumber);
}
