package com.publishing.clients.category;

import com.publishing.clients.category.dto.CategoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "category-service",
        url = "localhost:8087"
)
public interface CategoryClient {

    @GetMapping(value = "dev/api/v1/categories/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    CategoryResponseDto getCategoryResponse(@PathVariable("categoryId") Integer categoryId);

}