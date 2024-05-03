package com.todopal.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notifications")
@Getter
@Setter
@AllArgsConstructor
public class Notification extends BaseEntity {
    private String username;
    private String text;
    private String time;
    private Boolean seen;
    private String todoId;
}
