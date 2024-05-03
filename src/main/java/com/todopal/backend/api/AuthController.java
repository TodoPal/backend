package com.todopal.backend.api;

import com.todopal.backend.dto.UserInputDto;
import com.todopal.backend.services.AuthService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserInputDto userInputDto) {
        return this.authService.loginUser(userInputDto);
    }

    @PostMapping(value = "/loginWithJwt", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String loginWithJwt(@RequestBody String token) {
        return this.authService.loginWithJwt(token);
    }
}
