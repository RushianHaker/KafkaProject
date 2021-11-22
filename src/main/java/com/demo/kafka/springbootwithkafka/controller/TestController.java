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
     * Записывает элемент в таблицу пользователей издателя
     *
     */
    @PostMapping(value = "/user")
    public void postUser(@RequestParam("id") int id, @RequestParam("name") String name){
        log.trace("[POST] postUser({},{})", id, name);
        this.producerService.sendMessage(id, name);
        log.trace("[POST] postUser({},{}) inserted", id, name);
    }

    /**
     * Возвращает записи элемента из таблицы пользователей издателя
     *
     */
    @GetMapping(value = "/users_list")
    public String getUsersList() {
        log.trace("[GET] getUsersList()");
        return consumerService.consume().toString();
    }

    /**
     * Возвращает записи элемента из таблицы логов подписчика
     *
     */
    @GetMapping(value = "/log_list")
    public String getLogList() {
        log.trace("[GET] getLogList()");
        return consumerService.consumeLog().toString();
    }
}