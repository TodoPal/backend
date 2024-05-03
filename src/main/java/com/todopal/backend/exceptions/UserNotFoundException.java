package com.todopal.backend.exceptions;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
