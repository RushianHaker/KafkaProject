package com.example.producingwebservice.repositoy;

import com.example.producingwebservice.model.ConsumerLog;

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
