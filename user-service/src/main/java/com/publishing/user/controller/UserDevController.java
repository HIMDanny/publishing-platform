package com.publishing.user.controller;

import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserResponseDto;
import com.publishing.exception.CustomUserException;
import com.publishing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/api/v1/users")
@RequiredArgsConstructor
public class UserDevController {

    private final UserService userService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponseDto getUser(@PathVariable("id") Integer id) throws CustomUserException {
        return userService.getUserResponse(id);
    }

    @GetMapping(params = "email")
    UserAuthResponseDto getByEmailToAuthenticate(@RequestParam("email") String email){
        return userService.getUserByEmailForAuth(email);
    }
}
