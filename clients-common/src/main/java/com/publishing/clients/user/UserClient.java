package com.publishing.clients.user;

import com.publishing.clients.user.dto.UserRequestDto;
import java.util.Optional;

import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
//        path = "http://localhost:8080",
        name = "userClient",
        url = "http://localhost:8080"
)
public interface UserClient {

    @PostMapping("api/v1/users")
    boolean saveUser(@RequestBody UserAuthResponseDto user);

    @GetMapping("dev/api/v1/users")
    UserAuthResponseDto getByEmailToAuthenticate(@RequestParam("email") String email);

    @GetMapping("dev/api/v1/users/{id}")
    UserResponseDto getUserResponse(@PathVariable("id") Integer id);
}