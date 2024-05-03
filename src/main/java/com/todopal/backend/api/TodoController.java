package com.todopal.backend.api;

import com.todopal.backend.dto.TodoDto;
import com.todopal.backend.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDto> getTodosForUser(@RequestParam String createdBy) {
        return this.todoService.getAllTodos(createdBy);
    }

    @GetMapping("/assigned")
    public List<TodoDto> getTodosAssignedForUser(@RequestParam String username) {
        return this.todoService.getAssignedTodos(username);
    }

    @PostMapping
    public TodoDto save(@RequestBody TodoDto todoDto) {
        return this.todoService.addTodo(todoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDto> delete(@PathVariable String id, @RequestParam String createdBy) {
        this.todoService.deleteTodo(id, createdBy);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public TodoDto getTodoById(@PathVariable String id) {
        return this.todoService.getTodoById(id);
    }

    @GetMapping("/getUsersFor/{id}")
    public List<String> getUsersForTodo(@PathVariable String id) {
        return this.todoService.getUsersForTodo(id);
    }

    @PatchMapping("/{id}/addUser")
    public TodoDto addUserForTodo(@PathVariable String id, @RequestParam String username, @RequestParam String time) {
        return this.todoService.addUserForTodo(id, username, time);
    }

    @PatchMapping("/{id}/removeUser")
    public TodoDto removeUserFromTodo(@PathVariable String id, @RequestParam String username) {
        return this.todoService.removeUserFromTodo(id, username);
    }

    @PatchMapping("/{id}")
    public TodoDto editTodo(@RequestBody TodoDto todoDto) {
        return this.todoService.editTodo(todoDto);
    }

}
