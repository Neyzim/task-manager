package com.task.taskmanager.bussiness.mapper;

import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.infrastructure.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.cache.support.NullValue;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateConverter {

    void autoUpdateTask(TaskDto dto, @MappingTarget TaskEntity entity);
}
