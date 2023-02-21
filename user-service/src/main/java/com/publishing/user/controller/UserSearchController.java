package com.publishing.user.controller;

import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.dto.UserPaginationParameters;
import com.publishing.user.service.UserPaginationService;
import com.publishing.user.service.UserSearchService;
import com.publishing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/search")
@RequiredArgsConstructor
public class UserSearchController {

    private final UserSearchService userSearchService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityUserResponseDto> searchUser(@RequestParam("value") String value,
                                                  @RequestParam(value = "field", required = false) String field,
                                                  @RequestParam(value = "direction", required = false) String direction){
        return userSearchService.searchUsers(value, field, direction);
    }

    @GetMapping
    public UserPageResponseDto searchUserWithPagination(@RequestParam("value") String value,
                                                        UserPaginationParameters params){
        return userSearchService.searchUserWithPagination(value, params);
    }

}
