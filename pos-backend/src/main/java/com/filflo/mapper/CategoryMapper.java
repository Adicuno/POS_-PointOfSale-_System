package com.filflo.mapper;

import com.filflo.modal.Category;
import com.filflo.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore().getId())
                .build();
    }
}
