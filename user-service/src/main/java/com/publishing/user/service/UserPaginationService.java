package com.publishing.user.service;

import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPaginationService extends UserServiceCommon{

    private final UserRepository userRepository;

    public UserPageResponseDto findUserWithPagination(Integer offset, Integer pageSize){
        Page<User> usersPage = userRepository.findAll(
                PageRequest.of(offset - 1, pageSize));

        return mapToPageDTO(offset, pageSize, usersPage);
    }

    public UserPageResponseDto findUserWithPaginationAndSort(String field, String dirVal,
                                                             Integer offset, Integer pageSize) {

        Sort.Direction direction = Sort.Direction.valueOf(dirVal);
        Page<User> usersPage = userRepository.findAll(
                PageRequest.of(offset - 1, pageSize).withSort(direction, field));

        return mapToPageDTO(offset, pageSize, usersPage);
    }

}
