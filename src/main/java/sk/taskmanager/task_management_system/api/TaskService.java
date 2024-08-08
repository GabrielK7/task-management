package sk.taskmanager.task_management_system.api;

import sk.taskmanager.task_management_system.api.request.TaskAddRequest;
import sk.taskmanager.task_management_system.api.request.TaskEditRequest;
import sk.taskmanager.task_management_system.api.request.UserAddRequest;
import sk.taskmanager.task_management_system.domain.Task;
import sk.taskmanager.task_management_system.domain.TaskStatus;

import java.util.List;

public interface TaskService {
    long add(TaskAddRequest request);

    void edit(long id, TaskEditRequest request);

    void changeStatus(long id, TaskStatus status);

    void assign(long taskId, long projectId);

    void delete(long taskId);

    Task get(long taskId);

    List<Task> getAll();

    List<Task> getAllByUser(long userId);

    List<Task> getAllByProject(long projectId);
}
