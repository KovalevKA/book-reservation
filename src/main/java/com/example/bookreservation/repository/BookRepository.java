package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

    @Query("SELECT * FROM book " +
        "WHERE book_id NOT IN " +
        "(SELECT book_id FROM reserv WHERE reservation_date_cancel > CURRENT_DATE) " +
        "AND book_id IN :ids " +
        "GROUP BY b.book_id")
    Flux<Book> getFreeBooksByListId(List<Long> ids);
/*
    @Query("SELECT b FROM Book b LEFT JOIN b.authorList a LEFT JOIN b.genreList g "
        + "LEFT JOIN b.translatorList t LEFT JOIN b.reservList r "
        + "WHERE UPPER(b.name) LIKE %:name% AND "
        + "a.id IN :aids AND "
        + "g.id IN :gids AND "
        + "t.id IN :tids AND "
        + "r.reservationDateCancel > :date "
        + "GROUP BY b.id")
    Flux<Book> getReservByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
        Date date);


    @Query("SELECT b FROM Book b LEFT JOIN b.authorList a LEFT JOIN b.genreList g "
        + "LEFT JOIN b.translatorList t LEFT JOIN b.reservList r "
        + "WHERE UPPER(b.name) LIKE %:name% AND "
        + "a.id IN :aids AND "
        + "g.id IN :gids AND "
        + "t.id IN :tids AND "
        + "r.reservationDateCancel <= :date "
        + "GROUP BY b.id")
    Flux<Book> getFreeByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
        Date date);
*/

}
