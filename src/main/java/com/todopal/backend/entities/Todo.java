package com.todopal.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Todos")
@Getter
@Setter
@AllArgsConstructor
public class Todo  extends BaseEntity {
    private String title;
    private String text;
    private String createdBy;
    private List<String> users;
    private String edited;
}
