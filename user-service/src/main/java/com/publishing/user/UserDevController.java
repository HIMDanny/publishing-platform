package com.publishing.user;

import com.publishing.clients.user.User;
import com.publishing.exception.CustomUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev/api/v1/users")
@RequiredArgsConstructor
public class UserDevController {

    private final UserService userService;

    @GetMapping("{id}")
    User getUser(@PathVariable("id") Integer id) throws CustomUserException {
        return userService.getUserResponse(id);
    }

}
