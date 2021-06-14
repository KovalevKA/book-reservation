package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Author;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends R2dbcRepository<Author, Long> {

    Mono<Author> findByNameIgnoreCase(String name);

    Flux<Author> findByNameContainsIgnoreCase(String name);

    Mono<Author> findByAuthorId(Long id);

    @Query("SELECT a.* FROM author a "
        + "LEFT JOIN author_book ab ON a.author_id = ab.author_id "
        + "WHERE ab.book_id = :id")
    Flux<Author> findByBookId(Long id);
}
