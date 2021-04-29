package com.example.bookreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class BookReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReservationApplication.class, args);
    }

}
