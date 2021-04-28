package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.name = :name")
    Genre getByName(@Param("name") String name);

    @Query("SELECT g FROM Genre g WHERE UPPER(g.name) LIKE %:name%")
    List<Genre> getGenresByNameLike (@Param("name") String name);

    @Query("SELECT g.id FROM Genre g")
    List<Long> geAllIdGenres();
}
