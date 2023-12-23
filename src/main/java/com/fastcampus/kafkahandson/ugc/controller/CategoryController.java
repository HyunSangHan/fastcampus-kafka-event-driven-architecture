package com.fastcampus.kafkahandson.ugc.controller;

import com.fastcampus.kafkahandson.ugc.Records;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "메타정보 API", description = "메타정보 서버에서 제공하는 각종 메타정보 API", version = "v1"))
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Operation(summary = "Category 전체 목록 조회", description = "카테고리 전체 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Records.Category>> list() {
        List<Records.Category> categories = Records.categoryMap.values().stream().toList();
        return ResponseEntity.ok().body(categories);
    }

    @Operation(summary = "Category 단건 조회", description = "categoryId를 통해 특정 카테고리 정보를 조회합니다.")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Records.Category> get(
        @PathVariable(name = "categoryId") Long id
    ) {
        Records.Category category = Records.categoryMap.get(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(category);
    }
}
