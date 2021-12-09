package com.demo.kafka.springbootwithkafka.repository;

import com.demo.kafka.springbootwithkafka.model.User;
import lombok.extern.slf4j.Slf4j;
import org.h2.message.DbException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс IUserRepository
 *
 * @author Max Ivanov
 */

@Repository
@Slf4j
public class UserRepository implements IUserRepository {

    private static final String SQL_SELECT_LIST = "SELECT id, name FROM user_table";
    private static final String SQL_INSERT = "INSERT INTO user_table (id, name) VALUES (?, ?)";

    protected final static UserMapper USER_MAPPER = new UserMapper();

    protected final JdbcTemplate template;
    public UserRepository(@Qualifier("demo") JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> getUsersList() throws DbException {
            return template.query(SQL_SELECT_LIST, USER_MAPPER);
    }

    @Override
    public void insert(User entity) throws DbException {
            var result = template.update(SQL_INSERT, entity.getId(), entity.getName());
            if (result != 1) log.trace("UserRepository.update() with {} rows inserted", entity);
            log.trace("insert({}) result={}", entity.toString(), result);
    }

    /**
     * {@link User} mapper
     */
    protected static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            var entity = new User(rs.getInt("id"), rs.getString("name"));

            log.trace("UserMapper(): entity = [{}]", entity.toString());
            return entity;
        }
    }
}