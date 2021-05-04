package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findGenreByNameIgnoreCase(String name);

    List<Genre> findGenreByNameContainsIgnoreCase(String name);
}
