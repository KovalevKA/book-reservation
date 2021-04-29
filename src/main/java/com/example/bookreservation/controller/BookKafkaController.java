package com.example.bookreservation.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookKafkaController {

    @KafkaListener(topics = { "test" })
    public String getTopics(@RequestBody String emp) {
        System.out.println("Kafka event consumed is: " + emp);
        return "OK";
    }
}
