package com.demo.kafka.springbootwithkafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Класс ConsumerLog
 *
 * @author Max Ivanov
 * created 22.11.2021
 */
@Data
@RequiredArgsConstructor
public class ConsumerLog {

    @JsonProperty("id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final int id;

    @JsonProperty("msg")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final String msg;

    @Override
    public String toString() {
        return "Was added log [id=" + id + ", log=" + msg + "]";
    }
}
