package com.task.taskmanager.controller;

import com.task.taskmanager.bussiness.TaskService;
import com.task.taskmanager.bussiness.dto.TaskDto;
import com.task.taskmanager.infrastructure.enums.NotificationStatusEnum;
import com.task.taskmanager.infrastructure.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<List<TaskDto>> getTaskByEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.getTasksByUserEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(String id) {
        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Task Not found");
        }
    }

    @PatchMapping
    public ResponseEntity<TaskDto> updateTaskNotificationStatus(@RequestParam("Status") NotificationStatusEnum status,
                                                                @RequestParam String id) {
        return ResponseEntity.ok(taskService.changeTaskStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto dto,
                                              @RequestParam String id){
        return ResponseEntity.ok(taskService.updateTask(dto, id));
    }
}