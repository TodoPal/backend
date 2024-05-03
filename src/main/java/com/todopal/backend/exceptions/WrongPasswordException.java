package com.todopal.backend.exceptions;

public class WrongPasswordException extends BaseException {
    public WrongPasswordException(String msg) {
        super(msg);
    }
}
