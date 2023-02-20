package com.publishing.user.service;

import com.publishing.clients.article.ArticleClient;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPaginationService extends UserServiceCommon{

    private final UserRepository userRepository;
    private final ArticleClient articleClient;

    public UserPageResponseDto findUserWithPagination(Integer offset, Integer pageSize){
        Page<User> usersPage = userRepository.findAll(PageRequest.of(offset - 1, pageSize));
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

    public UserPageResponseDto findUserWithPaginationAndSort(String field,
                                                             String dirVal,
                                                             Integer offset,
                                                             Integer pageSize){
        Sort.Direction direction = Sort.Direction.valueOf(dirVal);
        Page<User> usersPage = userRepository.findAll(
                PageRequest.of(offset - 1, pageSize).withSort(direction, field));

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
