package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Translator;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TranslatorRepository extends R2dbcRepository<Translator, Long> {

    Mono<Translator> findByName(String name);

    Flux<Translator> findByNameContainsIgnoreCase(String name);

    Mono<Translator> findByTranslatorId(Long id);
}
