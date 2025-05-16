package com.demo.exception;

import static com.demo.Constants.USERNAME_ALREADY_EXISTS;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format(USERNAME_ALREADY_EXISTS, username));
    }
}