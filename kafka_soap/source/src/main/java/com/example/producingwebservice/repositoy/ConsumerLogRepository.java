package com.example.producingwebservice.repositoy;

import com.example.producingwebservice.model.ConsumerLog;
import lombok.extern.slf4j.Slf4j;
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

    private static final String SQL_SELECT_LIST = "SELECT id, message, date_time FROM log";
    private static final String SQL_INSERT = "INSERT INTO log (message, date_time) VALUES (?, ?)";

    protected final static ConsumerLogMapper CONSUMER_LOG_MAPPER = new ConsumerLogMapper();

    protected final JdbcTemplate template;

    public ConsumerLogRepository(@Qualifier("demo") JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<ConsumerLog> getLog() {
        return template.query(SQL_SELECT_LIST, CONSUMER_LOG_MAPPER);
    }

    @Override
    public void insert(ConsumerLog entity) {
        var result = template.update(SQL_INSERT, entity.getMsg(), entity.getData());
        if (result != 1) log.trace("ConsumerLogRepository.update() with {} rows inserted", entity);
        log.trace("insert({}) result={}", entity, result);
    }

    /**
     * {@link ConsumerLog} mapper
     */
    protected static class ConsumerLogMapper implements RowMapper<ConsumerLog> {
        @Override
        public ConsumerLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            var data = rs.getDate("date_time");
            var entity = new ConsumerLog(
                    rs.getString("message"),
                    data == null ? null : data.toLocalDate()
            );

            log.trace("ConsumerLogMapper(): entity = [{}]", entity);
            return entity;
        }
    }
}