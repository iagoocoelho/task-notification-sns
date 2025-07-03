package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Notification;
import com.example.taskmanager.domain.NotificationPublishMessage;
import com.example.taskmanager.domain.NotificationRequest;
import com.example.taskmanager.repository.NotificationRepository;
import com.example.taskmanager.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    private final PushNotificationService pushNotificationService;

    public NotificationController(PushNotificationService service) {
        this.pushNotificationService = service;
    }



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
    public String sendPushNotification(
            @RequestBody NotificationPublishMessage notificationPublishMessage
            ) {
        return pushNotificationService.enviarPushParaDispositivo(notificationPublishMessage);
    }
}
