package com.todopal.backend.repositories;

import com.todopal.backend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);

    void deleteByUsername(String username);
}
