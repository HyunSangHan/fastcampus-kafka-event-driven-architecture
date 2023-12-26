package com.fastcampus.kafkahandson.ugc.consumer;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.SubscribingPostAddToInboxService;
import com.fastcampus.kafkahandson.ugc.SubscribingPostListUsecase;
import com.fastcampus.kafkahandson.ugc.SubscribingPostRemoveFromInboxUsecase;
import com.fastcampus.kafkahandson.ugc.adapter.common.OperationType;
import com.fastcampus.kafkahandson.ugc.adapter.common.Topic;
import com.fastcampus.kafkahandson.ugc.adapter.inspectedpost.InspectedPostMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContentSubscribingWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    private final SubscribingPostAddToInboxService subscribingPostAddToInboxService;
    private final SubscribingPostRemoveFromInboxUsecase subscribingPostRemoveFromInboxUsecase;

    @KafkaListener(
        topics = { Topic.INSPECTED_POST },
        groupId = "subscribing-post-consumer-group",
        concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        InspectedPostMessage inspectedPostMessage = objectMapper.readValue(message.value(), InspectedPostMessage.class);
        if (inspectedPostMessage.getOperationType() == OperationType.CREATE) {
            this.handleCreate(inspectedPostMessage);
        } else if (inspectedPostMessage.getOperationType() == OperationType.UPDATE) {
            // DO NOTHING
        } else if (inspectedPostMessage.getOperationType() == OperationType.DELETE) {
            this.handleDelete(inspectedPostMessage);
        }
    }

    private void handleCreate(InspectedPostMessage inspectedPostMessage) {
        subscribingPostAddToInboxService.saveSubscribingInboxPost(inspectedPostMessage.getPayload().getPost());
    }

    private void handleDelete(InspectedPostMessage inspectedPostMessage) {
        subscribingPostRemoveFromInboxUsecase.deleteSubscribingInboxPost(inspectedPostMessage.getId());
    }
}
