package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.PostInspectUsecase;
import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal")
public class InternalController {

    private final PostInspectUsecase postInspectUsecase;

    @GetMapping
    InspectedPost inspectionTest(
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("categoryId") Long categoryId
    ) {
        return postInspectUsecase.inspectAndGetIfValid(Post.generate(
            0L, // userId는 검수 결과에 영향을 미치지 않으므로 아무거나 입력
            title,
            content,
            categoryId
        ));
    }
}
