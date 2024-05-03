package com.todopal.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity {
    private String username;
    private String password;
    private String joined;
}
