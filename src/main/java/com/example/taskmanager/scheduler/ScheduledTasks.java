package com.example.taskmanager.scheduler;

import com.example.taskmanager.domain.Notification;
import com.example.taskmanager.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private NotificationRepository notificationRepository;



    @Scheduled(fixedRate = 5000)
    public void verifyPendentTasks() {
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeNowAfter5Minutes = timeNow.plusMinutes(5);

        List<Notification> listNotifications = notificationRepository.findByDateTimeBetweenAndNotifiedFalse(timeNow, timeNowAfter5Minutes);


        for (Notification notification : listNotifications) {
            // envia pra service sqs
        }

        Date currentDate = new Date();
        log.info("The time is now {}", dateFormat.format(currentDate));
    }
}
