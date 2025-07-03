package com.example.taskmanager.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationPublishMessage {
    private String endpointArn;
    private String title;
    private String message;
}
