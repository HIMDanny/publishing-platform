package com.publishing.category.controller;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.category.service.CategoryPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories/pagination")
@RequiredArgsConstructor
public class CategoryPaginationController {

    private final CategoryPaginationService categoryPaginationService;

    @GetMapping(params = {"offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryPageResponseDto getCategoriesWithPagination(
                                    @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return categoryPaginationService.findCategoriesWithPagination(offset, pageSize);
    }

    @GetMapping(params = {"field", "direction", "offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryPageResponseDto getCategoriesWithPaginationAndSort(
            @RequestParam(value = "offset", defaultValue = "1") Integer offset,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "field", defaultValue = "ASC") String field,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return categoryPaginationService.findCategoriesWithPaginationAndSorting(offset, pageSize, field, direction);
    }
}
