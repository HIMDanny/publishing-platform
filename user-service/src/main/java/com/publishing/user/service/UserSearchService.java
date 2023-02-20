package com.publishing.user.service;

import com.publishing.clients.article.ArticleClient;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSearchService extends UserServiceCommon{

    private final UserRepository userRepository;
    private final ArticleClient articleClient;

    public List<EntityUserResponseDto> searchUsers(String value){
        return userRepository.searchUsers(value).stream()
                .peek(user -> user.setArticles(articleClient.getArticleResponsesByUser(user.getId())))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserPageResponseDto searchUserWithPagination(String value, Integer offset, Integer pageSize){
        Page<User> usersPage = userRepository.searchUserWithPagination(
                value, PageRequest.of(offset - 1, pageSize));

        return mapToPageDTO(offset, pageSize, usersPage);
    }

}
