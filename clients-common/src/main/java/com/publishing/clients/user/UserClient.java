package com.publishing.clients.user;

import com.publishing.clients.user.dto.UserRequestDto;

import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("user")
public interface UserClient {

    @PostMapping("api/v1/users")
    Integer saveUser(UserRequestDto user, @RequestParam(value = "image", required = false) MultipartFile image);

    @GetMapping("dev/api/v1/users")
    UserAuthResponseDto getByEmailToAuthenticate(@RequestParam("email") String email);

    @GetMapping("dev/api/v1/users/{id}")
    UserResponseDto getUserResponse(@PathVariable("id") Integer id);
}