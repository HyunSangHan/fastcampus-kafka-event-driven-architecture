package com.fastcampus.kafkahandson.ugc.port;

import java.util.List;

public interface MetadataPort {

    String getCategoryNameByCategoryId(Long categoryId);
    String getUserNameByUserId(Long userId);
    List<Long> listFollowerIdsByUserId(Long userId);
}
