package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  Genre findByNameIgnoreCase(String name);

  List<Genre> findByNameContainsIgnoreCase(String name);

  @Query("SELECT g.id FROM Genre g")
  List<Long> getAllIds();
}
