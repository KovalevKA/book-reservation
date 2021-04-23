package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {

    @Query("SELECT t FROM Translator t WHERE t.name = :name")
    Translator getByName(@Param("name") String name);
}
