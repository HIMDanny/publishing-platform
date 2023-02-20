package com.publishing.user.service;

import com.publishing.clients.article.ArticleClient;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public abstract class UserServiceCommon {

    @Autowired
    private ArticleClient articleClient;

    protected EntityUserResponseDto mapToDto(User user) {
        return EntityUserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .articles(user.getArticles())
                .build();
    }

    protected UserPageResponseDto mapToPageDTO(Integer offset, Integer pageSize, Page<User> usersPage) {
        List<EntityUserResponseDto> users = usersPage.stream()
                .peek(user -> user.setArticles(articleClient.getArticleResponsesByUser(user.getId())))
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
