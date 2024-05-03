package com.todopal.backend.services;

import com.todopal.backend.dto.UserProfileDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final UserService userService;
    private final TodoService todoService;

    public ProfileService(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    public UserProfileDto getProfile(String username) {
        var user = this.userService.findUserByUsername(username);
        var todosCreated = this.todoService.findAllByCreatedBy(username).size();
        var todosWithOthers = this.todoService.findAllByUsersContains(username).size();
        return new UserProfileDto(user.getUsername(), user.getJoined(), todosCreated, todosWithOthers);
    }
}
