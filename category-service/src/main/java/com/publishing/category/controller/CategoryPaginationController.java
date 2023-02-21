package com.publishing.category.controller;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.category.dto.CategoryPaginationParameters;
import com.publishing.category.service.CategoryPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories/pagination")
@RequiredArgsConstructor
public class CategoryPaginationController {

    private final CategoryPaginationService categoryPaginationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryPageResponseDto getCategoriesWithPaginationAndSort(CategoryPaginationParameters params){
        return categoryPaginationService.findCategoriesWithPaginationAndSorting(params);
    }
}
