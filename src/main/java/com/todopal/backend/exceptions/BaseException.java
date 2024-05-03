package com.todopal.backend.exceptions;

public abstract class BaseException extends RuntimeException {
    public BaseException(String msg) {
        super(msg);
    }
}
