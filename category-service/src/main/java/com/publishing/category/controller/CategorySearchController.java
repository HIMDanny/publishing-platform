package com.publishing.category.controller;

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

    @GetMapping(params = {"value"})
    @ResponseStatus(HttpStatus.OK)
    public List<EntityCategoryResponseDto> searchCategories(@RequestParam("value") String value){
        return categorySearchService.searchCategories(value);
    }

    @GetMapping(params = {"value", "offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public List<EntityCategoryResponseDto> searchCategories(@RequestParam("value") String value,
                                                            @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return categorySearchService.searchCategoriesWithPagination(value, offset, pageSize);
    }
}