package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Notification;
import com.example.taskmanager.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;


    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
        notificationRepository.save(notification);
        return ResponseEntity.ok("Notificação enviada com sucesso!");
    }
}
