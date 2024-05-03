package com.todopal.backend.mapper;

import com.todopal.backend.dto.TodoDto;
import com.todopal.backend.entities.Todo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoDto todo2TodoDto(Todo todo) {
        return new TodoDto(
                todo.getId().toString(),
                todo.getTitle(),
                todo.getText(),
                todo.getCreatedBy(),
                todo.getUsers(),
                todo.getEdited()
        );
    }

    public Todo todoDto2Todo(TodoDto todoDto) {
        var todo = new Todo(
                todoDto.getTitle(),
                todoDto.getText(),
                todoDto.getCreatedBy(),
                todoDto.getUsers(),
                todoDto.getEdited()
        );
        if (todoDto.getId() == null) {
            todo.setId(new ObjectId());
        } else {
            todo.setId(new ObjectId(todoDto.getId()));
        }

        return todo;
    }
}
