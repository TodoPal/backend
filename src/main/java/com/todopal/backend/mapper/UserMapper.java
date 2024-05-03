package com.todopal.backend.mapper;

import com.todopal.backend.dto.UserDto;
import com.todopal.backend.dto.UserInputDto;
import com.todopal.backend.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userDto2User(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                null,
                userDto.getJoined()
        );
    }

    public User userInputDto2User(UserInputDto userInputDto) {
        return new User(
                userInputDto.getUsername(),
                userInputDto.getPassword(),
                userInputDto.getJoined()
        );
    }

    public UserDto user2UserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getJoined()
        );
    }
}
