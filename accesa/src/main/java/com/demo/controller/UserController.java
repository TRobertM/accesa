package com.demo.controller;

import com.demo.dto.UsernamePasswordDTO;
import com.demo.dto.UserDTO;
import com.demo.service.implementations.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsernamePasswordDTO loginRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.loginUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UsernamePasswordDTO registerRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.registerUser(registerRequest));
    }
}
