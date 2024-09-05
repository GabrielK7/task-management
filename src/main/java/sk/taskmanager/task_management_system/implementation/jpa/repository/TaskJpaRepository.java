package sk.taskmanager.task_management_system.implementation.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.taskmanager.task_management_system.domain.Task;
import sk.taskmanager.task_management_system.implementation.jpa.entity.ProjectEntity;
import sk.taskmanager.task_management_system.implementation.jpa.entity.TaskEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByUserId(long userId);
    List<TaskEntity> findAllByProjectId(long projectId);
}
