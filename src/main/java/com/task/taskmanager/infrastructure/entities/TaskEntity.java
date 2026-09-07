package com.task.taskmanager.infrastructure.entities;

import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime scheduledDate;
    private String createdBy;
    private LocalDateTime changedAt;
    private NotificationStatusEnum status;
}
