package com.publishing.clients.category;

import com.publishing.clients.category.dto.CategoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "category-service",
        url = "localhost:8087"
)
public interface CategoryClient {

    @GetMapping("dev/api/v1/categories/{categoryId}")
    CategoryResponseDto getCategoryResponse(@PathVariable("categoryId") Integer categoryId);

}
