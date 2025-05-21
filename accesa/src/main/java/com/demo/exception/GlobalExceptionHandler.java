package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<String> invalidValueException(InvalidValueException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> userDoesNotExistException(UserDoesNotExistException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> usernameAlreadyExistsException(UsernameAlreadyExistsException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPackageUnitException.class)
    public ResponseEntity<String> invalidPackageUnitException(InvalidPackageUnitException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
