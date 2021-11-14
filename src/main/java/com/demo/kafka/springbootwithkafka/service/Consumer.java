package com.demo.kafka.springbootwithkafka.service;

import com.demo.kafka.springbootwithkafka.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Класс Consumer
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Slf4j
@Service
public class Consumer {
    protected final IUserRepository repo;

    public Consumer(IUserRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume() throws IOException {
        var result = repo.getUsersList();

        result.forEach(message -> log.debug("#### -> Consumed received message [{}]",
                message.toString()));

    }
}
