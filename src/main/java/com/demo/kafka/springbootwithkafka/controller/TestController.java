package com.demo.kafka.springbootwithkafka.controller;

import com.demo.kafka.springbootwithkafka.service.Consumer;
import com.demo.kafka.springbootwithkafka.service.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * Класс TestController
 *
 * @author Max Ivanov
 * created 16.09.2021
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/kafka")
public class TestController {

    private final Producer producerService;
    private final Consumer consumerService;

    /**
     * Записывает элемент в таблицу пользователей
     *
     * @see "https://redmine.r77.center-inform.ru/issues/162447"
     */
    @PostMapping(value = "/user")
    public void postUser(@RequestParam("id") int id, @RequestParam("name") String name){
        log.trace("[POST] postUser({},{})", id, name.toString());
        this.producerService.sendMessage(id, name);
        log.trace("[POST] postUser({},{}) inserted", id, name.toString());
    }

    /**
     * Возвращает запись элемента из таблицы пользователей
     *
     * @see "https://redmine.r77.center-inform.ru/issues/162447"
     */
    @GetMapping(value = "/users_list")
    public void getUsersList() {
        log.trace("[GET] getUsersList()");
        this.consumerService.consume();
    }
}