package sk.taskmanager.task_management_system.implementation.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sk.taskmanager.task_management_system.domain.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
@Component
public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Project(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getTimestamp("createdAt").toLocalDateTime().atOffset(ZoneOffset.UTC)
        );
    }
}
