package com.task.taskmanager.infrastructure.security;


import com.neyzimho.user.bussiness.dto.UserDto;
import com.task.taskmanager.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserByUsername(String email, String token){
        System.out.println("TOKEN::::" + token);
        UserDto userDto = userClient.getUserByEmail(email, token);


        return User
                .withUsername(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
