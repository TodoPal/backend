package com.todopal.backend.api;

import com.todopal.backend.dto.UserInputDto;
import com.todopal.backend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUser(@RequestBody UserInputDto userInputDto) {
        return this.userService.saveUser(userInputDto);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@RequestBody UserInputDto userInputDto) {
        this.userService.deleteUser(userInputDto);
    }
}
