package com.demo.exception;

import static com.demo.Constants.PRODUCT_DOES_NOT_EXIST;

public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException() {
        super(PRODUCT_DOES_NOT_EXIST);
    }
}
