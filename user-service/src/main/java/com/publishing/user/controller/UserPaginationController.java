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

    @GetMapping("{offset}/{pageSize}")
    public UserPageResponseDto findUsersWithPagination(@PathVariable("offset") Integer offset,
                                                       @PathVariable("pageSize") Integer pageSize){
        return userPaginationService.findUserWithPagination(offset, pageSize);
    }

    @GetMapping(path = "{offset}/{pageSize}", params = {"field", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public UserPageResponseDto findUsersWithPaginationAndSort(@PathVariable("offset") Integer offset,
                                                              @PathVariable("pageSize") Integer pageSize,
                                                              @RequestParam("field") String field,
                                                              @RequestParam("direction") String direction){
        return userPaginationService.findUserWithPaginationAndSort(field, direction, offset, pageSize);
    }

}
