package com.publishing.user;

import com.publishing.clients.user.User;
import com.publishing.exception.CustomUserException;
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
    User getUser(@PathVariable("id") Integer id) throws CustomUserException {
        return userService.getUserResponse(id);
    }

}
