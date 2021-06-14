package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreRepository extends R2dbcRepository<Genre, Long> {

    Mono<Genre> findByNameIgnoreCase(String name);

    Flux<Genre> findByNameContainsIgnoreCase(String name);

    Mono<Genre> findByGenreId(Long id);
}
