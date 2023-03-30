package com.publishing.category.dto;

import com.publishing.clients.category.dto.CategoryResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterSortingCategoriesDto {
    private Character letter;
    private List<CategoryResponseDto> categories;
}
