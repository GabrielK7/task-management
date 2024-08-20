package sk.taskmanager.task_management_system.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.taskmanager.task_management_system.api.TaskService;
import sk.taskmanager.task_management_system.api.request.TaskAddRequest;
import sk.taskmanager.task_management_system.api.request.TaskAssignStatusRequest;
import sk.taskmanager.task_management_system.api.request.TaskChangeStatusRequest;
import sk.taskmanager.task_management_system.api.request.TaskEditRequest;
import sk.taskmanager.task_management_system.domain.Task;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping

    public ResponseEntity<List<Task>> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long projectId
    ) {
        if (userId != null) {
            return ResponseEntity.ok().body(taskService.getAllByUser(userId));
        } else if (projectId != null) {
            return ResponseEntity.ok().body(taskService.getAllByProject(projectId));
        }

        return ResponseEntity.ok().body(taskService.getAll());
    }

    @PostMapping

    public ResponseEntity<Long> add(@RequestBody TaskAddRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.add(request));
    }

    @PutMapping("{id}")

    public ResponseEntity<Void> edit(@PathVariable("id") long id, @RequestBody TaskEditRequest request) {
        taskService.edit(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/status")

    public ResponseEntity<Void> changeStatus(@PathVariable("id") long id, @RequestBody TaskChangeStatusRequest request) {
        taskService.changeStatus(id, request.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/assign")

    public ResponseEntity<Void> assign(@PathVariable("id") long id, @RequestBody TaskAssignStatusRequest request) {
        taskService.assign(id, request.getProjectId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")

    public ResponseEntity<Task> get(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(taskService.get(id));
    }

    @DeleteMapping("{id}")

    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
