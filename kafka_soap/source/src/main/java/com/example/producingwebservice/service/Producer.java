package com.example.producingwebservice.service;

import com.example.producingwebservice.model.ConsumerLog;
import com.example.producingwebservice.repositoy.IConsumerLogRepository;
import io.spring.guides.gs_producing_web_service.Country;
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
    protected final IConsumerLogRepository repo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(IConsumerLogRepository repo) {
        this.repo = repo;
    }

    public void sendMessage(Country msg) {

        if (msg.getName() == null) {
            log.info("#### Null message");
            return;
        }

        var consumer = new ConsumerLog(msg.toString());
        repo.insert(consumer);

        log.info("#### Producing message [country={}]", msg);
        kafkaTemplate.send(TOPIC, "Writing in log -> " + msg.getName());
    }
}
