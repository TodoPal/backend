package com.todopal.backend.repositories;

import com.todopal.backend.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> getNotificationsByUsername(String username);
}
