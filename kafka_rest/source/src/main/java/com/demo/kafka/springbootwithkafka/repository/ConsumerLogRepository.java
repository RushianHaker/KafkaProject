package com.demo.kafka.springbootwithkafka.repository;

import com.demo.kafka.springbootwithkafka.model.ConsumerLog;
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
public class ConsumerLogRepository implements IConsumerLogRepository {

    private static final String SQL_SELECT_LIST = "SELECT id, message FROM log";
    private static final String SQL_INSERT = "INSERT INTO log (message) VALUES (?)";

    protected final static ConsumerLogMapper CONSUMER_LOG_MAPPER = new ConsumerLogMapper();

    protected final JdbcTemplate template;

    public ConsumerLogRepository(@Qualifier("demo") JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<ConsumerLog> getLog() throws DbException {
        return template.query(SQL_SELECT_LIST, CONSUMER_LOG_MAPPER);
    }

    @Override
    public void insert(ConsumerLog entity) throws DbException {
        var result = template.update(SQL_INSERT, entity.getMsg());
        if (result != 1) log.trace("ConsumerLogRepository.update() with {} rows inserted", entity);
        log.trace("insert({}) result={}", entity, result);
    }

    /**
     * {@link ConsumerLog} mapper
     */
    protected static class ConsumerLogMapper implements RowMapper<ConsumerLog> {
        @Override
        public ConsumerLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            var entity = new ConsumerLog(
                    rs.getInt("id"),
                    rs.getString("message")
            );

            log.trace("ConsumerLogMapper(): entity = [{}]", entity);
            return entity;
        }
    }
}