package com.task.taskmanager.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user", url = "${user.api.url}")
public interface UserClient {

    @GetMapping
    com.neyzimho.user.bussiness.dto.UserDto getUserByEmail(String email,
                                                           @RequestHeader("Authorization") String token);


}
