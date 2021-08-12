package com.example.bookreservation.kafkaListener;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.BookService;
import com.example.bookreservation.service.elasticSearch.CommonElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;

@EnableKafka
public class BookKafkaListener {

    @Autowired
    private BookService bookService;

    @Autowired
    private CommonElasticSearchService<BookDTO> elasticSearchService;

    @KafkaListener(topics = {"book-add-topic"})
    public void getTopics(@RequestBody BookDTO data) throws Exception {
        String id = bookService.create(data).getId();
        data.setId(id);
        elasticSearchService.add(data);
    }
}
