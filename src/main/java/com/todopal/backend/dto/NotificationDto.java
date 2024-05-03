package com.todopal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDto {
    private String id;
    private String username;
    private String text;
    private String time;
    private Boolean seen;
    private String todoId;
}
