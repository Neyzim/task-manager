package com.task.taskmanager.bussiness.dto;

import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStatusDto {

    private String id;
    private NotificationStatusEnum notificationStatusEnum;

}
