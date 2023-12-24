package com.fastcampus.kafkahandson.ugc.consumer;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.PostInspectUsecase;
import com.fastcampus.kafkahandson.ugc.adapter.common.OperationType;
import com.fastcampus.kafkahandson.ugc.adapter.common.Topic;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessage;
import com.fastcampus.kafkahandson.ugc.adapter.originalpost.OriginalPostMessageConverter;
import com.fastcampus.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fastcampus.kafkahandson.ugc.port.InspectedPostMessageProducePort;
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
    private final InspectedPostMessageProducePort inspectedPostMessageProducePort;

    @KafkaListener(
        topics = { Topic.ORIGINAL_POST },
        groupId = "auto-inspection-consumer-group",
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
        InspectedPost inspectedPost = postInspectUsecase.inspectAndGetIfValid(
            OriginalPostMessageConverter.toModel(originalPostMessage)
        );
        if (inspectedPost == null) {
            return;
        }
        inspectedPostMessageProducePort.sendCreateMessage(inspectedPost);
    }

    private void handleUpdate(OriginalPostMessage originalPostMessage) {
        InspectedPost inspectedPost = postInspectUsecase.inspectAndGetIfValid(
            OriginalPostMessageConverter.toModel(originalPostMessage)
        );
        if (inspectedPost == null) {
            inspectedPostMessageProducePort.sendDeleteMessage(originalPostMessage.getId());
        } else {
            inspectedPostMessageProducePort.sendUpdateMessage(inspectedPost);
        }
    }

    private void handleDelete(OriginalPostMessage originalPostMessage) {
        // DELETE 메시지는 검수가 필요 없으므로 바로 삭제
        inspectedPostMessageProducePort.sendDeleteMessage(originalPostMessage.getId());
    }
}
