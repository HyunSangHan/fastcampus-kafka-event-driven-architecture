package com.fastcampus.kafkahandson.ugc.consumer;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.PostInspectUsecase;
import com.fastcampus.kafkahandson.ugc.adapter.common.Topic;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessage;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessageConverter;
import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AutoInspectionWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    private final PostInspectUsecase postInspectUsecase;

    @KafkaListener(
        topics = { Topic.ORIGINAL_POST },
        groupId = "auto-inspection-consumer-group",
        concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        OriginalPostMessage originalPostMessage = objectMapper.readValue(message.value(), OriginalPostMessage.class);
        InspectedPost inspectedPost = postInspectUsecase.inspectAndGetIfValid(
            OriginalPostMessageConverter.toModel(originalPostMessage)
        );
        System.out.println(inspectedPost);
    }
}
