package com.publishing.user.controller;

import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.dto.UserPaginationParameters;
import com.publishing.user.service.UserPaginationService;
import com.publishing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/pagination")
@RequiredArgsConstructor
public class UserPaginationController {

    private final UserPaginationService userPaginationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserPageResponseDto findUsersWithPaginationAndSort(UserPaginationParameters params){
        return userPaginationService.findUserWithPaginationAndSort(params);
    }

}
