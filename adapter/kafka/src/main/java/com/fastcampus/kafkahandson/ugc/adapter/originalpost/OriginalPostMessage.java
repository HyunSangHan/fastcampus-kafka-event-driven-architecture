package com.fastcampus.kafkahandson.ugc.adapter.originalpost;

import com.fastcampus.kafkahandson.ugc.adapter.common.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OriginalPostMessage {
    private Long id;
    private Payload payload;
    private OperationType operationType;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Payload {
        private Long id;
        private String title;
        private String content;
        private Long userId;
        private Long categoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
    }
}
