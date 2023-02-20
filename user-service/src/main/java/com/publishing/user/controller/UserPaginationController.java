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

    @GetMapping(params = {"offset", "pageSize"})
    public UserPageResponseDto findUsersWithPagination(@RequestParam("offset") Integer offset,
                                                       @RequestParam("pageSize") Integer pageSize){
        return userPaginationService.findUserWithPagination(offset, pageSize);
    }

    @GetMapping(params = {"offset", "pageSize", "field", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public UserPageResponseDto findUsersWithPaginationAndSort(@RequestParam("offset") Integer offset,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("field") String field,
                                                              @RequestParam("direction") String direction){
        return userPaginationService.findUserWithPaginationAndSort(field, direction, offset, pageSize);
    }

}
