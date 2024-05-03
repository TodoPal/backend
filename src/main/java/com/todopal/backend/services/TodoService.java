package com.todopal.backend.services;

import com.todopal.backend.dto.TodoDto;
import com.todopal.backend.entities.Notification;
import com.todopal.backend.entities.Todo;
import com.todopal.backend.entities.User;
import com.todopal.backend.exceptions.TodoNotFoundException;
import com.todopal.backend.exceptions.UnauthorizedException;
import com.todopal.backend.mapper.TodoMapper;
import com.todopal.backend.repositories.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoService {
    private final TodoRepository repository;

    private final UserService userService;
    private final NotificationService notificationService;

    private final TodoMapper todoMapper;

    public TodoService(
            TodoRepository todoRepository,
            UserService userService,
            NotificationService notificationService,
            TodoMapper todoMapper
    ) {
        this.repository = todoRepository;
        this.userService = userService;
        this.notificationService = notificationService;
        this.todoMapper = todoMapper;
    }

    public List<TodoDto> getAllTodos(String createdBy) {
        return this.repository.findAllByCreatedBy(createdBy)
                .stream().map(todoMapper::todo2TodoDto)
                .collect(Collectors.toList());
    }

    public void deleteTodo(String id, String createdBy) {
        var todo = this.repository.findById(id).orElseThrow(() -> {
            log.error("Todo: " + id + " not found.");
            return new TodoNotFoundException(id);
        });
        if (!todo.getCreatedBy().equals(createdBy)) {
            throw new UnauthorizedException(MessageFormat.format("User {0} cannot delete todo with id: {1}", createdBy, id));
        }
        this.repository.deleteById(id);
    }

    public TodoDto addTodo(TodoDto todoDto) {
        var savedTodo = this.repository.save(this.todoMapper.todoDto2Todo(todoDto));
        return this.todoMapper.todo2TodoDto(savedTodo);
    }

    public List<String> getUsersForTodo(String todoId) {
        var todo = this.repository.findById(todoId).orElseThrow(() -> {
            log.error("Todo: " + todoId + " not found.");
            return new TodoNotFoundException(todoId);
        });
        var sort = Sort.by(Sort.Direction.ASC, "username");
        return this.userService.getUsers(sort).stream()
                .filter(user -> !user.getUsername().equals(todo.getCreatedBy()))
                .filter(user -> {
                    if (todo.getUsers() == null || todo.getUsers().isEmpty()) {
                        return true;
                    } else {
                        return !todo.getUsers().contains(user.getUsername());
                    }
                })
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    public TodoDto addUserForTodo(String todoId, String username, String time) {
        var todo = this.repository.findById(todoId).orElseThrow(() -> {
            log.error("Todo: " + todoId + " not found.");
            return new TodoNotFoundException(todoId);
        });
        this.userService.findUserByUsername(username);
        if (!todo.getUsers().contains(username)) {
            todo.getUsers().add(username);
        }
        this.notificationService.saveNotification(
                new Notification(
                        username,
                        todo.getCreatedBy() + " assigned a todo to you",
                        time,
                        false,
                        todoId
                )
        );
        return this.todoMapper.todo2TodoDto(this.repository.save(todo));
    }

    public TodoDto removeUserFromTodo(String todoId, String username) {
        var todo = this.repository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        todo.getUsers().remove(username);
        return this.todoMapper.todo2TodoDto(this.repository.save(todo));
    }

    public TodoDto editTodo(TodoDto todoDto) {
        var todo = this.repository.findById(todoDto.getId()).orElseThrow(() -> new TodoNotFoundException(todoDto.getId()));

        if (todoDto.getTitle() != null) {
            todo.setTitle(todoDto.getTitle());
        }
        if (todoDto.getText() != null) {
            todo.setText(todoDto.getText());
        }
        todo.setEdited(todoDto.getEdited());

        return this.todoMapper.todo2TodoDto(this.repository.save(todo));
    }

    public List<TodoDto> getAssignedTodos(String username) {
        return this.repository.findAllByUsersContains(username)
                .stream().map(todoMapper::todo2TodoDto)
                .collect(Collectors.toList());
    }

    public TodoDto getTodoById(String todoId) {
        var todo = this.repository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        return this.todoMapper.todo2TodoDto(todo);
    }

    public List<Todo> findAllByCreatedBy(String username) {
        return this.repository.findAllByCreatedBy(username);
    }

    public List<Todo> findAllByUsersContains(String username) {
        return this.repository.findAllByUsersContains(username);
    }
}
