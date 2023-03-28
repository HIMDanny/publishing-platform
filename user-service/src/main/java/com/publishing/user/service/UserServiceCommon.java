package com.publishing.user.service;

import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.ArticleClient;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.util.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class UserServiceCommon {

    @Autowired
    private ArticleClient articleClient;
    @Autowired
    private FileStorageProperties properties;

    protected Map<String, String> toMap(PaginationParameters params){
        return new HashMap<>(){
            {
                this.put("page", String.valueOf(params.getPage()));
                this.put("pageSize", String.valueOf(params.getPageSize()));
                this.put("field", params.getField());
                this.put("direction", params.getDirection());
            }
        };
    }

    protected EntityUserResponseDto mapToDto(User user) {
        String imagePath = properties.getUploadDir() + File.separator + user.getId() + File.separator + user.getImage();
        return EntityUserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .image(imagePath)
                .role(user.getRole())
                .page(user.getPage())
                .build();
    }

    protected UserPageResponseDto mapToPageDTO(Integer offset, Integer pageSize, Page<User> usersPage) {
        List<EntityUserResponseDto> users = usersPage.stream()
                .peek(user -> user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                        user.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
                )))
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return UserPageResponseDto.builder()
                .totalElements(usersPage.getTotalElements())
                .totalPages(usersPage.getTotalPages())
                .page(offset)
                .pageSize(pageSize)
                .users(users)
                .build();
    }

}
