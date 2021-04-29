package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {

    @Query("SELECT t FROM Translator t WHERE t.name = :name")
    Translator getByName(@Param("name") String name);

    @Query("SELECT t FROM Translator t WHERE UPPER(t.name) LIKE %:name%")
    List<Translator> getTranslatorsByNameLike (@Param("name") String name);

    @Query("SELECT t.id FROM Translator t")
    List<Long> getAllIdTranslators ();
}
