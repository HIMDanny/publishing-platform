package com.publishing.clients.user;

import com.publishing.clients.auth.RegisterRequest;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        path = "http://localhost:8080",
        name = "userClient",
        url = "http://localhost:8080"
)
public interface UserClient {

    @PostMapping("api/v1/user")
    boolean saveUser(@RequestBody RegisterRequest user);

    @GetMapping("api/v1/user/{email}")
    Optional<RegisterRequest> getByEmail(@PathVariable String email);
}