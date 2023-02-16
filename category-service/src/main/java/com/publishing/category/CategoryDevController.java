package com.publishing.category;

import com.publishing.clients.category.CategoryResponse;
import com.publishing.exception.CategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev/api/v1/categories")
@RequiredArgsConstructor
public class CategoryDevController {

    private final CategoryService categoryService;

    @GetMapping("/dev/api/v1/categories/{categoryId}")
    public CategoryResponse getCategoryResponse(@PathVariable("categoryId") Integer categoryId) throws CategoryException {
        return categoryService.getCategoryResponse(categoryId);
    }

}
