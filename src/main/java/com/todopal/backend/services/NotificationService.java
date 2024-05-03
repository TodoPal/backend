package com.todopal.backend.services;

import com.todopal.backend.dto.NotificationDto;
import com.todopal.backend.entities.Notification;
import com.todopal.backend.exceptions.NotificationNotFoundException;
import com.todopal.backend.mapper.NotificationMapper;
import com.todopal.backend.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationMapper notificationMapper;

    public NotificationService(
            NotificationRepository repository,
            NotificationMapper notificationMapper
    ) {
        this.repository = repository;
        this.notificationMapper = notificationMapper;
    }

    public void saveNotification(Notification notification) {
        this.repository.save(notification);
    }

    public void toggleNotification(String notificationId) {
        var notification = this.repository.findById(notificationId).orElseThrow(() -> {
            var errorMsg = "Notification: " + notificationId + " not found.";
            log.error(errorMsg);
            return new NotificationNotFoundException(errorMsg);
        });
        notification.setSeen(!notification.getSeen());
        this.repository.save(notification);
    }

    public List<NotificationDto> getNotificationsForUser(String username) {
        return this.repository.getNotificationsByUsername(username).stream()
                .map(notificationMapper::notification2NotificationDto)
                .collect(Collectors.toList());
    }
}
