package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Notification;
import com.example.taskmanager.domain.NotificationRequest;
import com.example.taskmanager.repository.NotificationRepository;
import com.example.taskmanager.service.SNSAwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    private final SNSAwsService snsAwsService;


    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody NotificationRequest notificationRequest) {
        Notification notificationToEntity = new Notification();
        notificationToEntity.setTitle(notificationRequest.getTitle());
        notificationToEntity.setTokenFcm(notificationRequest.getTokenFcm());
        notificationToEntity.setDateTime(notificationRequest.getDateTime());
        notificationToEntity.setNotified(false);


        notificationRepository.save(notificationToEntity);
        return ResponseEntity.ok("Notificação enviada com sucesso!");
    }


    @PostMapping("/sns")
    public String sendNotification(@RequestBody String message) {
        snsAwsService.publishMessage("my-topic", message);
        return "Notification sent successfully!";
    }
}
