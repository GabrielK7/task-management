package sk.taskmanager.task_management_system.implementation.jdbc.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.taskmanager.task_management_system.api.ProjectService;
import sk.taskmanager.task_management_system.api.TaskService;
import sk.taskmanager.task_management_system.api.UserService;
import sk.taskmanager.task_management_system.api.exception.BadRequestException;
import sk.taskmanager.task_management_system.api.request.TaskAddRequest;
import sk.taskmanager.task_management_system.api.request.TaskEditRequest;
import sk.taskmanager.task_management_system.domain.Project;
import sk.taskmanager.task_management_system.domain.Task;
import sk.taskmanager.task_management_system.domain.TaskStatus;
import sk.taskmanager.task_management_system.implementation.jdbc.repository.TaskJdbcRepository;

import java.util.List;
@Service
public class TaskServiceJdbcImpl implements TaskService {
    private final TaskJdbcRepository repository;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskServiceJdbcImpl(TaskJdbcRepository repository, ProjectService projectService, UserService userService) {
        this.repository = repository;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public long add(TaskAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void edit(long taskId, TaskEditRequest request) {
        if (this.get(taskId) != null) {
            repository.update(taskId, request);
        }
    }

    @Override
    public void changeStatus(long taskId, TaskStatus status) {
        if (this.get(taskId) != null) {
            repository.updateStatus(taskId, status);
        }
    }

    @Override
    public void assign(long taskId, long projectId) {
        final Task task = this.get(taskId);
        final Project project = projectService.get(projectId);

        if (task != null && project != null) {
            if (task.getUserId() != project.getUserId()) {
                throw new BadRequestException("Task and project must belong to the same user");
            }
            repository.updateProject(taskId, projectId);
        }
    }

    @Override
    public void delete(long taskId) {
        if (this.get(taskId) != null) {
            repository.delete(taskId);
        }
    }

    @Override
    public Task get(long taskId) {
        return repository.getById(taskId);
    }

    @Override
    public List<Task> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Task> getAllByUser(long userId) {
        if (userService.get(userId) != null) {
            return repository.getAllByUser(userId);
        }

        return null;
    }

    @Override
    public List<Task> getAllByProject(long projectId) {
        if (projectService.get(projectId) != null) {
            return repository.getAllByProject(projectId);
        }

        return null;
    }
}
