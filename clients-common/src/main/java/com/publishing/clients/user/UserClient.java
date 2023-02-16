package com.publishing.clients.user;

import com.publishing.clients.auth.RegisterRequest;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
//        path = "http://localhost:8080",
        name = "userClient",
        url = "http://localhost:8080"
)
public interface UserClient {

    @PostMapping("api/v1/users")
    boolean saveUser(@RequestBody RegisterRequest user);

    @GetMapping("api/v1/users/{email}")
    Optional<RegisterRequest> getByEmail(@PathVariable("email") String email);

    @GetMapping("api/v1/users/{id}")
    public User getUser(@PathVariable("id") Integer id);
}