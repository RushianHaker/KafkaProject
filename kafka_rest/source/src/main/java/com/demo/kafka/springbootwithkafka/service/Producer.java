package com.demo.kafka.springbootwithkafka.service;

import com.demo.kafka.springbootwithkafka.model.User;
import com.demo.kafka.springbootwithkafka.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * Класс Producer
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Service
@Slf4j
public class Producer {

    private static final String TOPIC = "users";
    protected final IUserRepository repo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(IUserRepository repo) {
        this.repo = repo;
    }

    public void sendMessage(int id, String name) {
        if (name == null) log.info("#### Null name message");
        var user = new User(id, name);
        repo.insert(user);

        log.info("#### Producing message [user={}]", user);
        kafkaTemplate.send(TOPIC, "Writing in log -> " + user);
    }
}
