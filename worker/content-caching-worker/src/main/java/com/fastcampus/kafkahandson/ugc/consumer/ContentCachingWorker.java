package com.fastcampus.kafkahandson.ugc.consumer;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.PostResolvingHelpUsecase;
import com.fastcampus.kafkahandson.ugc.adapter.common.OperationType;
import com.fastcampus.kafkahandson.ugc.adapter.common.Topic;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessage;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessageConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContentCachingWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    private final PostResolvingHelpUsecase postResolvingHelpUsecase;

    @KafkaListener(
        topics = { Topic.ORIGINAL_POST },
        groupId = "cache-post-consumer-group",
        concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        OriginalPostMessage originalPostMessage = objectMapper.readValue(message.value(), OriginalPostMessage.class);
        if (originalPostMessage.getOperationType() == OperationType.CREATE) {
            this.handleCreate(originalPostMessage);
        } else if (originalPostMessage.getOperationType() == OperationType.UPDATE) {
            this.handleUpdate(originalPostMessage);
        } else if (originalPostMessage.getOperationType() == OperationType.DELETE) {
            this.handleDelete(originalPostMessage);
        }
    }

    private void handleCreate(OriginalPostMessage originalPostMessage) {
        postResolvingHelpUsecase.resolvePostAndSave(OriginalPostMessageConverter.toModel(originalPostMessage));
    }

    private void handleUpdate(OriginalPostMessage originalPostMessage) {
        postResolvingHelpUsecase.resolvePostAndSave(OriginalPostMessageConverter.toModel(originalPostMessage));
    }

    private void handleDelete(OriginalPostMessage originalPostMessage) {
        postResolvingHelpUsecase.deleteResolvedPost(originalPostMessage.getId());
    }
}
