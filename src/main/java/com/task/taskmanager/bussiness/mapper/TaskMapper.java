package com.task.taskmanager.bussiness.mapper;

import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskEntity toTaskEntity(TaskDto taskDto);

    TaskDto toTaskDto(TaskEntity taskEntity);

    List<TaskEntity> toListTaskEntity(List<TaskDto> taskDtoList);

    List<TaskDto> toListTaskDto(List<TaskEntity> taskEntityList);
}
