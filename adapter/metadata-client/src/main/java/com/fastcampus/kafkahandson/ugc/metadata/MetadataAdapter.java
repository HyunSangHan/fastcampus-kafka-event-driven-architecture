package com.fastcampus.kafkahandson.ugc.metadata;

import com.fastcampus.kafkahandson.ugc.port.MetadataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MetadataAdapter implements MetadataPort {

    private final MetadataClient metadataClient;

    @Override
    public String getCategoryNameByCategoryId(Long categoryId) {
        MetadataClient.CategoryResponse categoryResponse = metadataClient.getCategoryById(categoryId);
        if (categoryResponse == null) return null;
        return categoryResponse.getName();
    }

    @Override
    public String getUserNameByUserId(Long userId) {
        MetadataClient.UserResponse userResponse = metadataClient.getUserById(userId);
        if (userResponse == null) return null;
        return userResponse.getName();
    }

    @Override
    public List<Long> listFollowerIdsByUserId(Long userId) {
        return metadataClient.getFollowerIdsByUserId(userId);
    }
}
