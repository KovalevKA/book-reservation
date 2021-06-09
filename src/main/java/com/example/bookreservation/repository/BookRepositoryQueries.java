package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import io.r2dbc.spi.ConnectionFactory;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BookRepositoryQueries {

    @Autowired
    private BookRepository bookRepository;
    @Qualifier("connectionFactory")
    @Autowired
    private ConnectionFactory connectionFactory;


    private final String baseSql = "SELECT b.* FROM book b "
        + "%s %s %s %s"
        + "WHERE UPPER(b.name) LIKE CONCAT(\'%%\', \'%s\' , \'%%\') "
        + "%s %s %s %s"
        + "GROUP BY b.book_id";
    private final String reservJoin = "LEFT JOIN reserv r on b.book_id = r.book_id ";
    private final String authorJoin = "LEFT JOIN author_book ab on b.book_id = ab.book_id "
        + "LEFT JOIN author a on ab.author_id = a.author_id ";
    private final String genreJoin = "LEFT JOIN genre_book gb on b.book_id = gb.book_id "
        + "LEFT JOIN genre g on gb.genre_id = g.genre_id ";
    private final String translatorJoin = "LEFT JOIN translator_book tb on b.book_id = tb.book_id "
        + "LEFT JOIN translator t on tb.translator_id = t.translator_id ";
    private final String authorCondition = "AND a.author_id IN ";
    private final String genreCondition = "AND g.genre_id IN ";
    private final String translatorCondition = "AND t.translator_id IN ";
    private final String reservCondition = "r.reservation_date_cancel <= \'%s\' ";

    public Mono<Book> findByBookId(Long id) {
        return bookRepository.findByBookId(id);
    }

    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    public Flux<Book> findByParams(Boolean isReserved, String bookName,
        List<Long> listAuthorId, List<Long> listGenreId, List<Long> listTranslatorsId,
        LocalDate date) {

        String sql = String.format(baseSql,
            isReserved ? reservJoin : "",
            listAuthorId.isEmpty() ? "" : authorJoin,
            listGenreId.isEmpty() ? "" : genreJoin,
            listTranslatorsId.isEmpty() ? "" : translatorJoin,
            bookName,
            listAuthorId.isEmpty() ? ""
                : getCondition(authorCondition, listAuthorId),
            listGenreId.isEmpty() ? ""
                : getCondition(genreCondition, listGenreId),
            listTranslatorsId.isEmpty() ? ""
                : getCondition(translatorCondition, listTranslatorsId),
            isReserved ? "" : "AND " + String.format(reservCondition, date.toString())
        );

        return executeQuery(sql);
    }

    private String getCondition(String condition, List<Long> ids) {
        return condition
            .concat(String.valueOf(ids))
            .replace('[', '(')
            .replace(']', ')');
    }

    private Flux<Book> executeQuery(String sql) {
        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
        return databaseClient
            .sql(sql)
            .fetch()
            .all()
            .map(objectMap -> new Book(
                (Long) objectMap.get("book_id"),
                (String) objectMap.get("name"),
                (String) objectMap.get("publishing_house"),
                (Integer) objectMap.get("publishing_year"),
                (String) objectMap.get("description")
            ));
    }

}
