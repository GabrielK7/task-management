package sk.taskmanager.task_management_system.implementation.jdbc.service;

import org.springframework.stereotype.Service;
import sk.taskmanager.task_management_system.api.ProjectService;
import sk.taskmanager.task_management_system.api.request.ProjectAddRequest;
import sk.taskmanager.task_management_system.api.request.ProjectEditRequest;
import sk.taskmanager.task_management_system.domain.Project;
import sk.taskmanager.task_management_system.implementation.jdbc.repository.ProjectJdbcRepository;
import sk.taskmanager.task_management_system.implementation.jdbc.repository.UserJdbcRepository;

import java.util.List;

@Service
public class ProjectServiceJdbcImpl implements ProjectService {
    ProjectJdbcRepository projectJdbcRepository;
    UserJdbcRepository userJdbcRepository;


    public ProjectServiceJdbcImpl() {
    }

    public ProjectServiceJdbcImpl(UserJdbcRepository userJdbcRepository, ProjectJdbcRepository projectJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
        this.projectJdbcRepository = projectJdbcRepository;
    }


    public ProjectServiceJdbcImpl(ProjectJdbcRepository jdbcRepository, UserJdbcRepository userJdbcRepository) {
        this.projectJdbcRepository = jdbcRepository;
        this.userJdbcRepository = userJdbcRepository;
    }

    @Override
    public Project get(long id) {
        return projectJdbcRepository.getById(id);
    }

    @Override
    public List<Project> getAll() {
        if (userJdbcRepository.getAll() != null) {
            return projectJdbcRepository.getAll();
        }
        return null;
    }

    @Override
    public List<Project> getAllByUser(long userId) {

        if (userJdbcRepository.getById(userId) != null) {
            return projectJdbcRepository.getAllByUser(userId);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        //TODO

       /* if(this.get(id !=null )){
            taskJdbcRepository.deleteAllByProject(id);
        }*/
        if (this.get(id) != null) {
            projectJdbcRepository.delete(id);
        }
    }

    @Override
    public long add(ProjectAddRequest request) {
        return projectJdbcRepository.add(request);
    }

    @Override
    public void edit(long id, ProjectEditRequest request) {
        if (this.get(id) != null) {
            projectJdbcRepository.update(id, request);
        }
    }
}
