package com.task.taskmanager.bussiness;

import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.bussiness.mapper.TaskMapper;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import com.task.taskmanager.infrastructure.repository.TaskRepository;
import com.task.taskmanager.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;

    public TaskDto saveTask(TaskDto taskDto, String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        taskDto.setCreationDate(LocalDateTime.now());
        taskDto.setStatus(NotificationStatusEnum.PENDING);
        taskDto.setCreatedBy(email);
        TaskEntity entity = taskMapper.toTaskEntity(taskDto);

        return taskMapper.toTaskDto(taskRepository.save(entity));
    }
}
