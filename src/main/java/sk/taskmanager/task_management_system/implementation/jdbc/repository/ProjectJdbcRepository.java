package sk.taskmanager.task_management_system.implementation.jdbc.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sk.taskmanager.task_management_system.api.exception.InternalErrorException;
import sk.taskmanager.task_management_system.api.exception.ResourceNotFoundException;
import sk.taskmanager.task_management_system.domain.Project;
import sk.taskmanager.task_management_system.implementation.jdbc.mapper.ProjectRowMapper;

import java.util.List;

@Repository
public class ProjectJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProjectRowMapper rowMapper;
    private static final Logger logger;
    private static final String GET_ALL;
    private static final String GET_BY_ID;
    private static final String GET_ALL_BY_USER;

    static {
        logger = LoggerFactory.getLogger(ProjectJdbcRepository.class);
        GET_ALL = "SELECT * FROM project";
        GET_BY_ID = "SELECT * FROM project WHERE id = ?";
        GET_ALL_BY_USER = "SELECT * FROM project WHERE user_id = ?";
    }

    public ProjectJdbcRepository(JdbcTemplate jdbcTemplate, ProjectRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Project> getAll() {
        try {
            return jdbcTemplate.query(GET_ALL, rowMapper);
        } catch (DataAccessException e) {
            logger.error("Error while getting all projects", e);
            throw new InternalErrorException("Error while getting all projects");
        }
    }

    public Project getById(long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Project by id " + id + " was not found");
        } catch (DataAccessException e) {
            logger.error("Error with getById project", e);
            throw new InternalErrorException("Error with getById project")
        }
    }

    public List<Project> getAllByUser(long userId) {
        try {
            return jdbcTemplate.query(GET_ALL_BY_USER, rowMapper, userId);
        } catch (DataAccessException e) {
            logger.error("Error while getting all projects by user", e);
            throw new InternalErrorException("Error while getting all projects by user");
        }
    }

}

