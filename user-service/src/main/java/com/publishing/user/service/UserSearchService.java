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
import com.publishing.clients.PaginationParameters;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSearchService extends UserServiceCommon{

    private final UserRepository userRepository;
    private final ArticleClient articleClient;

    public List<EntityUserResponseDto> searchUsers(String value, String fieldVal, String dirVal){
        Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        String field = fieldVal == null ? "id" : fieldVal;

        return userRepository.searchUsers(value, Sort.by(direction, field)).stream()
                .peek(user -> user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                        user.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build()))))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserPageResponseDto searchUserWithPagination(String value, PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<User> usersPage = userRepository.searchUserWithPagination(
                value, PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(direction, params.getField()));

        return mapToPageDTO(params.getPage(), params.getPageSize(), usersPage);
    }

}
