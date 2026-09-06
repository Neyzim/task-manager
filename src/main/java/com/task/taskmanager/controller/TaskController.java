package com.task.taskmanager.controller;

import com.task.taskmanager.bussiness.TaskService;
import com.task.taskmanager.bussiness.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto dto,
                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.saveTask(dto, token));
    }

    @GetMapping("/scheduled")
    public ResponseEntity<List<TaskDto>> getTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate
    ) {
        return ResponseEntity.ok(taskService.getTaskPerPeriod(startDate, finalDate));
    }
    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskDto>> getTaskByEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(taskService.getTasksByUserEmail(token));
    }
}