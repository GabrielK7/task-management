package sk.taskmanager.task_management_system.implementation.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.taskmanager.task_management_system.implementation.jpa.entity.UserEntity;
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
