package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {

    Translator findTranslatorByName(String name);

    List<Translator> findTranslatorsByNameContainsIgnoreCase(String name);
}
