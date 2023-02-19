package com.publishing;

import com.publishing.category.repo.CategoryRepository;
import com.publishing.category.dto.CategoryRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class CategoryApplicationTests {

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("category-service-integration-tests-db")
            .withUsername("test")
            .withPassword("test");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;
    @Autowired
    private CategoryRepository categoryRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void shouldCreateCategory() throws Exception {
        CategoryRequestDto categoryRequestDto = getCategoryRequest();
        String categoryRequestString = mapperBuilder.build().writeValueAsString(categoryRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, categoryRepository.findAll().size());
    }

    private CategoryRequestDto getCategoryRequest() {
        return CategoryRequestDto.builder()
                .name("Sport")
                .build();
    }

}
