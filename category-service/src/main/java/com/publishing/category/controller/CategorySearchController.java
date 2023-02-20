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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntityCategoryResponseDto> searchCategories(@RequestParam("query") String query){
        return categorySearchService.searchCategories(query);
    }

    @GetMapping("{offset}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityCategoryResponseDto> searchCategories(@RequestParam("query") String query,
                                                            @PathVariable("offset") Integer offset,
                                                            @PathVariable("pageSize") Integer pageSize){
        return categorySearchService.searchCategoriesWithPagination(query, offset, pageSize);
    }

}
