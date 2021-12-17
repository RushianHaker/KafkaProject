package com.example.producingwebservice.service;

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

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        if (msg == null) {
            log.info("#### Null message");
            return;
        }
        log.info("#### Producing message [country={}]", msg);
        kafkaTemplate.send(TOPIC, "Writing in log -> " + msg);
    }
}
