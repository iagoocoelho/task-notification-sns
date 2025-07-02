package com.example.taskmanager.repository;

import com.example.taskmanager.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDateTimeBetweenAndNotifiedFalse(LocalDateTime start, LocalDateTime end);
}