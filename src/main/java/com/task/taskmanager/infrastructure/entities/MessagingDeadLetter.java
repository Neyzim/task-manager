package com.task.taskmanager.infrastructure.entities;

import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("messaging-failure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessagingDeadLetter {

    @Id
    private String id;
    private String topic;
    private String message;
    private String stackTrace;
    private LocalDateTime failureDate;
}
