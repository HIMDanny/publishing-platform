package com.publishing.category;

import com.publishing.clients.category.CategoryResponse;
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
    public CategoryResponse getCategoryResponse(@PathVariable("categoryId") Integer categoryId) throws CategoryException {
        return categoryService.getCategoryResponse(categoryId);
    }

}
