package sk.taskmanager.task_management_system.implementation.jpa.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sk.taskmanager.task_management_system.api.UserService;
import sk.taskmanager.task_management_system.api.request.UserAddRequest;
import sk.taskmanager.task_management_system.domain.User;
import sk.taskmanager.task_management_system.implementation.jpa.repository.UserJpaRepository;

import java.util.List;
@Service
@Primary
public class UserServiceJpaImpl implements UserService {
  private final  UserJpaRepository repository;

    public UserServiceJpaImpl(UserJpaRepository jpaRepository, UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public long add(UserAddRequest request) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }
}
