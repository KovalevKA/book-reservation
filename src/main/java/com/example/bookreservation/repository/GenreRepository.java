package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

    Mono<Genre> findByNameIgnoreCase(String name);

    Flux<Genre> findByNameContainsIgnoreCase(String name);

    Mono<Genre> findByGenreId(Long id);
}
