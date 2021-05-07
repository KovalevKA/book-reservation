package com.example.bookreservation.kafkaListener;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;

@EnableKafka
public class BookKafkaListener {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private BookService bookService;

  @KafkaListener(topics = {"book-add-topic"})
  public void getTopics(@RequestBody BookDTO data) {
    System.out.println("Kafka event consumed is: " + bookService.create(data));
  }
}
