package com.publishing.category.controller;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.category.dto.CategoryPaginationParameters;
import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.service.CategorySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories/search")
@RequiredArgsConstructor
public class CategorySearchController {

    private final CategorySearchService categorySearchService;

    @GetMapping(path = "all")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityCategoryResponseDto> searchCategories(@RequestParam("value") String value,
                                                            @RequestParam(value = "field", required = false) String field,
                                                            @RequestParam(value = "direction", required = false) String direction){
        return categorySearchService.searchCategories(value, field, direction);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryPageResponseDto searchCategories(@RequestParam("value") String value,
                                                    CategoryPaginationParameters params){
        return categorySearchService.searchCategoriesWithPagination(value, params);
    }
}
