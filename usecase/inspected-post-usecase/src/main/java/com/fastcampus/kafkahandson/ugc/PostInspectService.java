package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.inspectedpost.model.AutoInspectionResult;
import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fastcampus.kafkahandson.ugc.port.MetadataPort;
import com.fastcampus.kafkahandson.ugc.port.PostAutoInspectPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class PostInspectService implements PostInspectUsecase {

    private final MetadataPort metadataPort;
    private final PostAutoInspectPort postAutoInspectPort;

    @Override
    public InspectedPost inspectAndGetIfValid(Post post) {
        String categoryName = metadataPort.getCategoryNameByCategoryId(post.getCategoryId());
        AutoInspectionResult inspectionResult = postAutoInspectPort.inspect(post, categoryName);
        if (!inspectionResult.getStatus().equals("GOOD")) return null;
        return InspectedPost.generate(
            post,
            categoryName,
            Arrays.asList(inspectionResult.getTags())
        );
    }
}
