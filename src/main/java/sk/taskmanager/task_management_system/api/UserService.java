package sk.taskmanager.task_management_system.api;

import org.springframework.beans.factory.annotation.Qualifier;
import sk.taskmanager.task_management_system.api.request.UserAddRequest;
import sk.taskmanager.task_management_system.domain.User;

import java.util.List;

public interface UserService{
    long add(UserAddRequest request);

    void delete(long id);

    User get(long id);

    List<User> getAll();
}
