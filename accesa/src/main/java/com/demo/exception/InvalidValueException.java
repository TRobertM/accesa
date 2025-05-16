package com.demo.exception;

import static com.demo.Constants.VALUE_CANNOT_BE_NULL;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
        super(VALUE_CANNOT_BE_NULL);
    }
}
