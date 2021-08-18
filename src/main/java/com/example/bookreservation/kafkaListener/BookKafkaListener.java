package com.example.bookreservation.kafkaListener;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@EnableKafka
//@Service
public class BookKafkaListener {

    @Autowired
    private BookService bookService;
    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = {"book-add-topic"})
    public void getTopics(@RequestBody String data) throws Exception {
        BookDTO bookDTO = mapper.readValue(data, BookDTO.class);
        System.out.println(bookDTO);
        String id = bookService.create(bookDTO).getId();
        bookDTO.setId(id);
    }
}
