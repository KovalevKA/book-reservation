package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Author findByNameIgnoreCase(String name);

  List<Author> findByNameContainsIgnoreCase(String name);

  @Query("SELECT a.id FROM Author a")
  List<Long> getAllIds();
}
