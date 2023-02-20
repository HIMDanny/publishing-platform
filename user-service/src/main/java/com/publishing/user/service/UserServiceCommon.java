package com.publishing.user.service;

import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.model.User;

public abstract class UserServiceCommon {

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

}
