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
    public CategoryPageResponseDto getCategoriesWithPagination(@PathVariable("offset") int offset,
                                                               @PathVariable("pageSize") int pageSize){
        return categoryPaginationService.findCategoriesWithPagination(offset, pageSize);
    }

    @GetMapping(params = {"field", "direction", "offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryPageResponseDto getCategoriesWithPaginationAndSort(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("field") String field,
            @RequestParam("direction") String direction){
        return categoryPaginationService.findCategoriesWithPaginationAndSorting(offset, pageSize, field, direction);
    }

}
