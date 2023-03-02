package com.publishing.category.service;

import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.model.Category;

public abstract class CategoryCommonService {
    protected EntityCategoryResponseDto mapToDto(Category category) {
        return EntityCategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .page(category.getPage())
                .build();
    }
}
