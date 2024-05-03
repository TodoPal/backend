package com.todopal.backend.services;

import com.auth0.jwt.JWT;
import com.todopal.backend.dto.UserInputDto;
import com.todopal.backend.jwt.JwtUtil;
import com.todopal.backend.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthService(
            UserService userService,
            JwtUtil jwtUtil,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    public String loginUser(UserInputDto userInputDto) {
        var user = this.userMapper.userInputDto2User(userInputDto);
        this.userService.checkUserCredential(user);

        return this.jwtUtil.getJwtToken(user.getUsername());
    }

    public String loginWithJwt(String token) {
        jwtUtil.verifyJwtToken(token);

        return JWT.decode(token).getClaim("username").toString().replace("\"", "");
    }
}
