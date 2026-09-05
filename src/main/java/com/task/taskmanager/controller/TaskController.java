package com.task.taskmanager.controller;

import com.task.taskmanager.bussiness.TaskService;
import com.task.taskmanager.bussiness.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto dto,
                                            @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(taskService.saveTask(dto, token));
    }
}
