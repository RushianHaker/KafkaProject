package com.example.producingwebservice.service;

import com.example.producingwebservice.model.ConsumerLog;
import com.example.producingwebservice.repositoy.IConsumerLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
    protected final IConsumerLogRepository consumerRepo;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consumeWriting(String message) {
        var consumerLog = new ConsumerLog(message);
        consumerRepo.insert(consumerLog);
        log.info("#### Consumed received message [{}]", message);
    }
}
