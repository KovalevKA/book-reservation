package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Author;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

    Mono<Author> findByNameIgnoreCase(String name);

    Flux<Author> findByNameContainsIgnoreCase(String name);

    @Query("SELECT a.author_id FROM author a")
    Flux<Long> getAllIds();
}
