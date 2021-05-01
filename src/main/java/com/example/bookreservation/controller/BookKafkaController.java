package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableKafka
@RestController
public class BookKafkaController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookService bookService;

    @KafkaListener(topics = {"book-add-topic"})
    public void getTopics(@RequestBody String data) throws JsonProcessingException {
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        System.out.println("Kafka event consumed is: " + bookService.create(data));
    }
}
