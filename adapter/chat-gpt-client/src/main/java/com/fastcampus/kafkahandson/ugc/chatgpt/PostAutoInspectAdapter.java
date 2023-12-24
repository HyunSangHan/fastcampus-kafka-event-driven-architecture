package com.fastcampus.kafkahandson.ugc.chatgpt;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.inspectedpost.model.AutoInspectionResult;
import com.fastcampus.kafkahandson.ugc.port.PostAutoInspectPort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAutoInspectAdapter implements PostAutoInspectPort {

    private final ChatGptClient chatGptClient;

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public AutoInspectionResult inspect(Post post, String categoryName) {
        String contentString = buildContentString(post, categoryName);
        ChatGptClient.ChatPolicy chatPolicy = new ChatGptClient.ChatPolicy(
            AutoInspectionPolicy.INSPECTION_INSTRUCTION,
            AutoInspectionPolicy.EXAMPLE_CONTENT,
            AutoInspectionPolicy.EXAMPLE_INSPECTION_RESULT
        );
        try {
            String resultString = chatGptClient.getResultForContentWithPolicy(
                contentString,
                chatPolicy
            );
            return objectMapper.readValue(resultString, AutoInspectionResult.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildContentString(Post post, String categoryName) {
        return String.format(
            "[%s] %s - %s",
            categoryName,
            post.getTitle(),
            post.getContent()
        );
    }

    static class AutoInspectionPolicy {
        private static final String INSPECTION_INSTRUCTION =
            "The task you need to accomplish is to return two items ('status' and 'tags') in JSON format. " +
                "The information I will provide will be in the format '[Post category] Post content.' " +
                "Then, if the content of the post aligns well with the meaning or theme of the post category, " +
                "fill the 'status' field with the string 'GOOD.' " +
                "If the meaning or theme appears unrelated, " +
                "fill the 'status' field with the string 'BAD.' " +
                "Additionally, extract and compile a list of up to 5 keywords " +
                "that seem most important in the post content and populate the 'tags' field with them.";
        private static final String EXAMPLE_CONTENT =
            "[Health] Reps and Muscle Size - To increase muscle size, " +
                "it is considered most ideal to exercise with the maximum weight " +
                "that allows 8 to 12 repetitions per set.";
        private static final String EXAMPLE_INSPECTION_RESULT =
            "{\"status\":\"GOOD\",\"tags\":[\"muscle\", \"weight\", \"repetitions\"]}";
    }
}
