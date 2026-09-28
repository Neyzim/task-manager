package com.task.taskmanager.infrastructure.repository;

import com.task.taskmanager.infrastructure.entities.TaskEntity;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByScheduledDateBetweenAndStatus(LocalDateTime startDate, LocalDateTime finalDate, NotificationStatusEnum status);

    List<TaskEntity> findBycreatedBy(String email);
}
