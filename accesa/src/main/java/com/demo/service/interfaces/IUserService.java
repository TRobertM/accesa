package com.demo.service.interfaces;

import com.demo.dto.UsernamePasswordDTO;
import com.demo.dto.UserDTO;

public interface IUserService {
    UserDTO registerUser(UsernamePasswordDTO registerRequest);
    String loginUser(UsernamePasswordDTO loginRequest);
}
