package com.example.producingwebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

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
    private int id;

    @JsonProperty("msg")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final String msg;

    @JsonProperty("data")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final LocalDate data;

    @Override
    public String toString() {
        return "Was added log [id=" + id + ", log=" + msg + "data=" + data.toString() + "]";
    }
}
