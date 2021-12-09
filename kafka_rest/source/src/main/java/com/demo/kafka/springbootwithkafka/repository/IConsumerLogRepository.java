package com.demo.kafka.springbootwithkafka.repository;

import com.demo.kafka.springbootwithkafka.model.ConsumerLog;
import com.demo.kafka.springbootwithkafka.model.User;

import java.util.List;

/**
 * Интерфейс IUserRepository
 *
 * @author Max Ivanov
 * created 08.11.2021
 */

public interface IConsumerLogRepository {

     List<ConsumerLog> getLog();

     void insert(ConsumerLog entity);
}
