package com.example.taskmanager.service;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SNSAwsService {

    private final SnsTemplate snsTemplate;

    public void publishMessage(String topicName, String message) {
        snsTemplate.sendNotification(topicName, message, "Subject: New Notification");
    }
}