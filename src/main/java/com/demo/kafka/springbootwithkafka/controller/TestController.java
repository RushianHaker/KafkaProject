package com.demo.kafka.springbootwithkafka.controller;

import com.demo.kafka.springbootwithkafka.model.User;
import com.demo.kafka.springbootwithkafka.service.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс TestController
 *
 * @author Max Ivanov
 * created 16.09.2021
 */

@Slf4j
@RestController
@RequestMapping(value = "/kafka")
public class TestController {
    private final Producer userService;

    @Autowired
    public TestController(Producer userService) {
        this.userService = userService;
    }

    /**
     * Возвращает запись элемента в справочнике видов и кодов продукции_v3
     *
     * @see "https://redmine.r77.center-inform.ru/issues/162447"
     */
    @PostMapping(value = "/user")
    public void postUser(@RequestParam("id") int id, @RequestParam("name") String name) {
        log.trace("[POST] postUser({},{})", id, name.toString());
        this.userService.sendMessage(id, name);
        log.trace("[POST] postUser({},{}) inserted", id, name.toString());
    }
}