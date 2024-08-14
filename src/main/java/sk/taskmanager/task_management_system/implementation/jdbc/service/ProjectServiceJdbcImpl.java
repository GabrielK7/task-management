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

    public ProjectServiceJdbcImpl(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    UserJdbcRepository userJdbcRepository;

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
        if (userJdbcRepository.getById(id) != null) {
            return projectJdbcRepository.getAll();
        }

        @Override
        public List<Project> getAllByUser(long userId){

            if (userJdbcRepository.getById(userId) != null) {
                return projectJdbcRepository.getAllByUser(userId);
            }
            return null;
        }

        @Override
        public void delete ( long id){

        }

        @Override
        public long add (ProjectAddRequest request){
            return 0;
        }

        @Override
        public void edit ( long id, ProjectEditRequest request){

        }
    }
