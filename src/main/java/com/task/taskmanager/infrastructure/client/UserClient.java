package com.task.taskmanager.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.neyzimho.user.bussiness.dto.UserDto;

@FeignClient(name = "user", url = "${user.api.url}")
public interface UserClient {

    @GetMapping("/user")
    UserDto getUserByEmail(@RequestParam String email,
                               @RequestHeader("Authorization") String token);


}
