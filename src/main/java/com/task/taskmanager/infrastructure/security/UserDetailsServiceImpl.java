package com.task.taskmanager.infrastructure.security;


import com.task.taskmanager.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.neyzimho.user.bussiness.dto.UserDto;
@Service
public class UserDetailsServiceImpl {

    private UserClient userClient;

    public UserDetails loadUserByUsername(String token, String email){
        UserDto userDto = userClient.getUserByEmail(email, token);

// Cria e retorna um objeto UserDetails com base no usuário encontrado
        return User
                .withUsername(userDto.getEmail()) // Define o nome de usuário como o e-mail
                .password(userDto.getPassword()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
