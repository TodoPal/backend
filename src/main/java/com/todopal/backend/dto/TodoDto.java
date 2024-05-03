package com.todopal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TodoDto {
    private String id;
    private String title;
    private String text;
    private String createdBy;
    private List<String> users;
    private String edited;
}
