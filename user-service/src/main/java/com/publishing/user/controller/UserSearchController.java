package com.publishing.user.controller;

import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<EntityUserResponseDto> searchUser(@RequestParam("value") String value){
        return userSearchService.searchUsers(value);
    }

    @GetMapping("{offset}/{pageSize}")
    public UserPageResponseDto searchUserWithPagination(@RequestParam("value") String value,
                                                        @PathVariable("offset") Integer offset,
                                                        @PathVariable("pageSize") Integer pageSize){
        return userSearchService.searchUserWithPagination(value, offset, pageSize);
    }

}
