package com.demo.exception;

import static com.demo.Constants.USER_DOES_NOT_EXIST;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super(USER_DOES_NOT_EXIST);
    }
}