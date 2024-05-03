package com.todopal.backend.api.errorhandling;

import com.todopal.backend.exceptions.TodoNotFoundException;
import com.todopal.backend.exceptions.UserAlreadyExistsException;
import com.todopal.backend.exceptions.UserNotFoundException;
import com.todopal.backend.exceptions.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> handleTodoNotFoundException(TodoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
