package sk.taskmanager.task_management_system.implementation.jdbc.service;

import org.springframework.stereotype.Service;
import sk.taskmanager.task_management_system.api.UserService;
import sk.taskmanager.task_management_system.api.request.UserAddRequest;
import sk.taskmanager.task_management_system.domain.User;
import sk.taskmanager.task_management_system.implementation.jdbc.repository.UserJdbcRepository;

import java.util.List;

@Service()
public class UserServiceJdbcImpl implements UserService {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceJdbcImpl(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    @Override
    public long add(UserAddRequest request) {
        return userJdbcRepository.add(request);
    }

    @Override
    public void delete(long id) {
        if(this.get(id) != null) {
            userJdbcRepository.delete(id);
        }
    }

    @Override
    public User get(long id) {
        return userJdbcRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userJdbcRepository.getAll();
    }
}
