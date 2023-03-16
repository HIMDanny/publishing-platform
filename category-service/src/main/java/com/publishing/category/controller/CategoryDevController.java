package com.publishing.category.controller;

import com.publishing.category.service.CategoryService;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.exception.CategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/api/v1/categories")
@RequiredArgsConstructor
public class CategoryDevController {

    private final CategoryService categoryService;

    @GetMapping("{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto getCategoryResponse(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.getCategoryResponse(categoryId);
    }

}
