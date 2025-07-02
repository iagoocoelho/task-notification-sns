package com.example.taskmanager.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String tokenFcm;
    private LocalDateTime dateTime;
    private Boolean notified;
}
