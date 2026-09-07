package com.task.taskmanager.bussiness;

import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.bussiness.mapper.TaskMapper;
import com.task.taskmanager.bussiness.mapper.TaskUpdateConverter;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import com.task.taskmanager.infrastructure.exceptions.ResourceNotFoundException;
import com.task.taskmanager.infrastructure.repository.TaskRepository;
import com.task.taskmanager.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;
    private final TaskUpdateConverter taskUpdateConverter;

    public TaskDto saveTask(TaskDto taskDto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        taskDto.setCreationDate(LocalDateTime.now());
        taskDto.setStatus(NotificationStatusEnum.PENDING);
        taskDto.setCreatedBy(email);
        TaskEntity entity = taskMapper.toTaskEntity(taskDto);

        return taskMapper.toTaskDto(taskRepository.save(entity));
    }

    public List<TaskDto> getTaskPerPeriod(LocalDateTime startDate, LocalDateTime finalDate) {
        return taskMapper.toListTaskDto(taskRepository.findByScheduledDateBetween(startDate, finalDate));
    }

    public List<TaskDto> getTasksByUserEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return taskMapper.toListTaskDto(taskRepository.findBycreatedBy(email));
    }

    public void deleteTaskById(String id) {
        taskRepository.deleteById(id);
    }

    public TaskDto changeTaskStatus(NotificationStatusEnum statusEnum, String id) {
        try {
            TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Task Not Found"));
            taskEntity.setStatus(statusEnum);
            return taskMapper.toTaskDto(taskRepository.save(taskEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa" + e.getCause());
        }
    }

    public TaskDto updateTask(TaskDto dto, String id) {
        try {
            TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Task Not Found"));
            taskUpdateConverter.autoUpdateTask(dto, taskEntity);
            return taskMapper.toTaskDto(taskRepository.save(taskEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa" + e.getCause());
        }
    }
}


