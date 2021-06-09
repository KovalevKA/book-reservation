package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {

    @Query("SELECT b.* FROM book b " +
        "WHERE b.book_id NOT IN " +
        "(SELECT book_id FROM reserv WHERE reservation_date_cancel > CURRENT_DATE) " +
        "AND b.book_id IN (:ids) " +
        "GROUP BY b.book_id")
    Flux<Book> getFreeBooksByListId(List<Long> ids);

    Mono<Book> findByBookId(Long id);

}
