package com.demo.mapper;

import com.demo.dto.UserDTO;
import com.demo.dto.UsernamePasswordDTO;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User toUser(UsernamePasswordDTO registerRequest) {
        return new User(
                registerRequest.username(),
                bCryptPasswordEncoder.encode(registerRequest.password())
        );
    }

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}
