package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {

  Translator findByName(String name);

  List<Translator> findByNameContainsIgnoreCase(String name);

  @Query("SELECT t.id FROM Translator t")
  List<Long> getAllIds();
}
