package com.task.taskmanager.infrastructure.messages.kafka.consumer;

import com.task.taskmanager.bussiness.TaskService;
import com.task.taskmanager.bussiness.dto.TaskStatusDto;
import com.task.taskmanager.infrastructure.entities.MessagingDeadLetter;
import com.task.taskmanager.infrastructure.repository.MessagingRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final TaskService taskService;
    private final MessagingRepository repository;

    @RetryableTopic(
            attempts = "3",
            backOff = @BackOff(delay = 3000, multiplier = 2.0),
            autoCreateTopics = "true",
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(topics = "status-notification", groupId = "manager-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeStatus(String message){

        TaskStatusDto dto = objectMapper.readValue(message, TaskStatusDto.class);
        taskService.changeTaskStatus(dto.getNotificationStatusEnum(), dto.getId());
    }

    @DltHandler
    public void recoverFailureMesssage(String message,
                                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                       Throwable exception){
        MessagingDeadLetter messagingDeadLetter = MessagingDeadLetter.builder()
                    .topic(topic)
                    .message(message)
                    .stackTrace(exception.getMessage())
                    .failureDate(LocalDateTime.now())
                .build();
        repository.save(messagingDeadLetter);
    }

}
