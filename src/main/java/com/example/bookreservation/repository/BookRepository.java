package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
