package com.example.taskmanager.service;

import com.example.taskmanager.domain.NotificationPublishMessage;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

@Service
public class PushNotificationService {

    private final SnsClient snsClient;

    public PushNotificationService() {
        this.snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public String enviarPushParaDispositivo(NotificationPublishMessage notificationPublishMessage) {
        String payload = gerarPayloadGCM(notificationPublishMessage.getTitle(), notificationPublishMessage.getMessage());

        PublishRequest publishRequest = PublishRequest.builder()
                .message(payload)
                .messageStructure("json")
                .targetArn(notificationPublishMessage.getEndpointArn())
                                 .build();

        PublishResponse response = snsClient.publish(publishRequest);
        return response.messageId();
    }

    private String gerarPayloadGCM(String titulo, String mensagem) {
        return "{ \"GCM\": \"{ \\\"notification\\\": { \\\"title\\\": \\\"" + titulo + "\\\", \\\"body\\\": \\\"" + mensagem + "\\\" } }\" }";
    }
}
