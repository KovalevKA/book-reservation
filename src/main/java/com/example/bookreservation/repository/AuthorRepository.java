package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByNameIgnoreCase(String name);

    List<Author> findAuthorsByNameContainsIgnoreCase(String name);
}
