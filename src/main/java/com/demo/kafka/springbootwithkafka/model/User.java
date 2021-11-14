package com.demo.kafka.springbootwithkafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс User
 *
 * @author Max Ivanov
 * created 08.11.2021
 */

@Data
@AllArgsConstructor
public class User {

    @JsonProperty("id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private int id;

    @JsonProperty("name")
    @JsonIgnoreProperties (ignoreUnknown = true)
    private String name;

    @Override
    public String toString() {
        return "Users " + "name is " + name + " and id - " + id;
    }
}
