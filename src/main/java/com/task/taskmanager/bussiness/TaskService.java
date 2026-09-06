package com.task.taskmanager.bussiness;

import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.bussiness.mapper.TaskMapper;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import com.task.taskmanager.infrastructure.repository.TaskRepository;
import com.task.taskmanager.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<TaskDto> getTaskPerPeriod(LocalDateTime startDate, LocalDateTime finalDate){
        return taskMapper.toListTaskDto(taskRepository.findByScheduledDateBetween(startDate, finalDate));
    }

    public List<TaskDto> getTasksByUserEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return taskMapper.toListTaskDto(taskRepository.findBycreatedBy(email));
        }
    }

