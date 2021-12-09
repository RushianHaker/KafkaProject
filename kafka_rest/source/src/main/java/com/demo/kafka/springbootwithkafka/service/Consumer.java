package com.demo.kafka.springbootwithkafka.service;

import com.demo.kafka.springbootwithkafka.model.ConsumerLog;
import com.demo.kafka.springbootwithkafka.model.User;
import com.demo.kafka.springbootwithkafka.repository.IConsumerLogRepository;
import com.demo.kafka.springbootwithkafka.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Класс Consumer
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class Consumer {
    protected final IUserRepository repo;
    protected final IConsumerLogRepository consumerRepo;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consumeWriting(String message) {
        var consumerLog = new ConsumerLog(1, message);
        consumerRepo.insert(consumerLog);
        log.info("#### Consumed received message [{}]", message);
    }

    public List<User> consume() {
        var list = repo.getUsersList();
        list.forEach(msg -> log.info("#### Consumer list users [{}]", msg.toString()));
        return list;
    }

    public List<ConsumerLog> consumeLog() {
        var list = consumerRepo.getLog();
        list.forEach(msg -> log.info("#### Consumer list log [{}]", msg.toString()));
        return list;
    }
}
