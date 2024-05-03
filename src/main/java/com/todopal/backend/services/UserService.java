package com.todopal.backend.services;

import com.todopal.backend.dto.UserInputDto;
import com.todopal.backend.entities.User;
import com.todopal.backend.exceptions.UserAlreadyExistsException;
import com.todopal.backend.exceptions.UserNotFoundException;
import com.todopal.backend.exceptions.WrongPasswordException;
import com.todopal.backend.mapper.UserMapper;
import com.todopal.backend.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public String saveUser(UserInputDto userInputDto) {
        var user = this.userMapper.userInputDto2User(userInputDto);
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));

            return this.repository.save(user).getId().toString();
        } catch (DuplicateKeyException e) {
            var errorMsg = MessageFormat.format("User {0} already exists.", user.getUsername());
            log.error(errorMsg);
            throw new UserAlreadyExistsException(errorMsg);
        }
    }

    public void deleteUser(UserInputDto userInputDto) {
        var user = this.userMapper.userInputDto2User(userInputDto);
        this.checkUserCredential(user);

        this.repository.deleteByUsername(user.getUsername());
    }

    public User findUserByUsername(String username) {
        return this.repository.findUserByUsername(username).orElseThrow(() -> {
            var errorMsg = MessageFormat.format("User: {0} not found.", username);
            log.error(errorMsg);
            return new UserNotFoundException(errorMsg);
        });
    }

    public boolean isUserExists(String username) {
        return this.repository.existsUserByUsername(username);
    }

    public List<User> getUsers(Sort sort) {
        return this.repository.findAll(sort);
    }

    public void checkUserCredential(User user) {
        var storedPwd = this.findUserByUsername(user.getUsername()).getPassword();

        if (!this.passwordEncoder.matches(user.getPassword(), storedPwd)) {
            var errorMsg = MessageFormat.format("Wrong password for user: {0}", user.getUsername());
            log.error(errorMsg);
            throw new WrongPasswordException(errorMsg);
        }
    }
}
