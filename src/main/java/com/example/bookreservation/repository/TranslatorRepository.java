package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TranslatorRepository extends ReactiveCrudRepository<Translator, Long> {

    Mono<Translator> findByName(String name);

    Flux<Translator> findByNameContainsIgnoreCase(String name);

    @Query("SELECT t.translator_id FROM translator t")
    Flux<Long> getAllIds();
}
