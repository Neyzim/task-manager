package com.task.taskmanager.infrastructure.repository;

import com.task.taskmanager.infrastructure.entities.MessagingDeadLetter;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessagingRepository extends MongoRepository<MessagingDeadLetter, String> {

}
