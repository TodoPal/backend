package com.todopal.backend.exceptions;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String errorMsg) {
        super(errorMsg);
    }
}
