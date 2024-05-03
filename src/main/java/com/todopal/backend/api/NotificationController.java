package com.todopal.backend.api;

import com.todopal.backend.dto.NotificationDto;
import com.todopal.backend.services.NotificationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PatchMapping("/{id}/toggle")
    public void toggleNotification(@PathVariable String id) {
        this.notificationService.toggleNotification(id);
    }

    @GetMapping("/{username}")
    public List<NotificationDto> getNotificationsForUser(@PathVariable String username) {
        return this.notificationService.getNotificationsForUser(username);
    }
}
