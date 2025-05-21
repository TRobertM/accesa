package com.demo.exception;

import static com.demo.Constants.INVALID_PACKAGE_UNIT;

public class InvalidPackageUnitException extends RuntimeException{
    public InvalidPackageUnitException(){
        super(INVALID_PACKAGE_UNIT);
    }
}
