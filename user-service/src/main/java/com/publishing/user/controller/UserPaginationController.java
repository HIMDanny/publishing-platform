package com.publishing.user.controller;

import com.publishing.user.dto.UserPageResponseDto;
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

    @GetMapping(params = {"page", "pageSize"})
    public UserPageResponseDto findUsersWithPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return userPaginationService.findUserWithPagination(page, pageSize);
    }

    @GetMapping(params = {"page", "pageSize", "field", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public UserPageResponseDto findUsersWithPaginationAndSort(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                              @RequestParam(value = "field", defaultValue = "ASC") String field,
                                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return userPaginationService.findUserWithPaginationAndSort(field, direction, page, pageSize);
    }

}
