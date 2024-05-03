package com.todopal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInputDto {
    private String username;
    private String password;
    private String joined;
}
