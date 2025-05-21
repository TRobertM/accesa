package com.demo.service.implementations;

import com.demo.dto.UsernamePasswordDTO;
import com.demo.dto.UserDTO;
import com.demo.exception.UserDoesNotExistException;
import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import com.demo.service.JWTService;
import com.demo.service.interfaces.IAuthService;
import com.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AuthService(UserValidator userValidator, UserMapper userMapper, UserRepository userRepository,
                       AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDTO registerUser(UsernamePasswordDTO registerRequest) {
        userValidator.validateUser(registerRequest.username());
        User user = userMapper.toUser(registerRequest);
        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    @Override
    public String loginUser(UsernamePasswordDTO loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow(UserDoesNotExistException::new);
        return jwtService.generateToken(user);
    }
}
