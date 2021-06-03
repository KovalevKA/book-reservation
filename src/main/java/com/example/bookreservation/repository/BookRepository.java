package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository extends R2dbcRepository<Book, Long> {

    @Query("SELECT b.* FROM book b " +
        "WHERE b.book_id NOT IN " +
        "(SELECT book_id FROM reserv WHERE reservation_date_cancel > CURRENT_DATE) " +
        "AND b.book_id IN (:ids) " +
        "GROUP BY b.book_id")
    Flux<Book> getFreeBooksByListId(List<Long> ids);

    @Query("SELECT b.* FROM book b "
        + "LEFT JOIN author_book ab on b.book_id = ab.book_id "
        + "LEFT JOIN author a on ab.author_id = a.author_id "
        + "LEFT JOIN genre_book gb on b.book_id = gb.book_id "
        + "LEFT JOIN genre g on gb.genre_id = g.genre_id "
        + "LEFT JOIN translator_book tb on b.book_id = tb.book_id "
        + "LEFT JOIN translator t on tb.translator_id = t.translator_id "
        + "LEFT JOIN reserv r on b.book_id = r.book_id "
        + "WHERE UPPER(b.name) LIKE CONCAT('%', :name, '%') AND "
        + "(a.author_id IN (:aids) OR TRUE) AND "
        + "(g.genre_id IN (:gids) OR TRUE) AND "
        + "(t.translator_id IN (:tids) OR TRUE) AND "
        + "r.reservation_date_cancel > :date "
        + "GROUP BY b.book_id")
    Flux<Book> getReservByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
        LocalDate date);


    @Query("SELECT b.* FROM book b "
        + "LEFT JOIN author_book ab on b.book_id = ab.book_id "
        + "LEFT JOIN author a on ab.author_id = a.author_id "
        + "LEFT JOIN genre_book gb on b.book_id = gb.book_id "
        + "LEFT JOIN genre g on gb.genre_id = g.genre_id "
        + "LEFT JOIN translator_book tb on b.book_id = tb.book_id "
        + "LEFT JOIN translator t on tb.translator_id = t.translator_id "
        + "LEFT JOIN reserv r on b.book_id = r.book_id "
        + "WHERE UPPER(b.name) LIKE CONCAT('%', :name, '%') AND "
        + "(a.author_id IN (:aids) OR TRUE) AND "
        + "(g.genre_id IN (:gids) OR TRUE) AND "
        + "(t.translator_id IN (:tids) OR TRUE) AND "
        + "r.reservation_date_cancel <= :date "
        + "GROUP BY b.book_id")
    Flux<Book> getFreeByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
        LocalDate date);

    Mono<Book> findByBookId(Long id);

}
