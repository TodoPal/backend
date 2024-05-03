package com.todopal.backend.mapper;

import com.todopal.backend.dto.NotificationDto;
import com.todopal.backend.entities.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDto notification2NotificationDto(Notification notification) {
        return new NotificationDto(
                notification.getId().toString(),
                notification.getUsername(),
                notification.getText(),
                notification.getTime(),
                notification.getSeen(),
                notification.getTodoId()
        );
    }
}
