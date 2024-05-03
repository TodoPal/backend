package com.todopal.backend.repositories;

import com.todopal.backend.entities.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    Optional<Todo> findByTitle(String name);

    Optional<Todo> findById(String id);

    List<Todo> findAllByCreatedBy(String username);

    List<Todo> findAllByUsersContains(String username);
}
