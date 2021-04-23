package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.name = :name")
    Genre getByName(@Param("name") String name);
}
