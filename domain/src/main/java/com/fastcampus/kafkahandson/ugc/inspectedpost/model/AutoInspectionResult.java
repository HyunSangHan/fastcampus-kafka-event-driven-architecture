package com.fastcampus.kafkahandson.ugc.inspectedpost.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoInspectionResult {
    private String status; // "GOOD" or "BAD"
    private String[] tags;
}
