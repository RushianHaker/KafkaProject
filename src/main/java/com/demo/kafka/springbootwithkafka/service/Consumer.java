package com.demo.kafka.springbootwithkafka.service;

import com.demo.kafka.springbootwithkafka.model.User;
import com.demo.kafka.springbootwithkafka.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @KafkaListener(topics = "users", groupId = "group_id")
    public List<User> consume() {
        var list = repo.getUsersList();
        list.forEach(msg -> log.info("#### ->  Consumed received message [{}]", msg.toString()));
        return list;
    }
}
