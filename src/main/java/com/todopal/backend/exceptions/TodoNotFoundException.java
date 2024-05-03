package com.todopal.backend.exceptions;

public class TodoNotFoundException extends BaseException {
    public TodoNotFoundException(String todoId) {
        super("Todo: " + todoId + " not found.");
    }
}
