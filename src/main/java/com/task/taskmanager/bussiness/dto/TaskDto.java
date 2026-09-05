package com.task.taskmanager.bussiness.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime scheduledDate;
    private String createdBy;
    private LocalDateTime changedAt;
    private NotificationStatusEnum status;
}
