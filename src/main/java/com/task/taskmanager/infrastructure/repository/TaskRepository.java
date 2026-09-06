package com.task.taskmanager.infrastructure.repository;

import com.task.taskmanager.infrastructure.entities.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByScheduledDateBetween(LocalDateTime startDate, LocalDateTime finalDate);

    List<TaskEntity> findBycreatedBy(String email);
}
