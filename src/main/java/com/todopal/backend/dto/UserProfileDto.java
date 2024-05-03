package com.todopal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    private String username;
    private String joined;
    private int todosCreated;
    private int todosWithOthers;
}
