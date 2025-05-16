package com.demo.validator;

import com.demo.exception.InvalidValueException;
import com.demo.exception.UsernameAlreadyExistsException;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(String username) {
        validateNotNull(username);
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
    }

    private void validateNotNull(Object... values) {
        for (Object value : values) {
            if (value == null) {
                throw new InvalidValueException();
            }
        }
    }
}
