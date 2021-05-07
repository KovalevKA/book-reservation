package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("SELECT b FROM Book b " +
      "WHERE b.id NOT IN " +
      "(SELECT r.book.id FROM Reserv r WHERE r.reservationDateCancel > CURRENT_DATE) " +
      "AND b.id IN :ids " +
      "GROUP BY b.id")
  List<Book> getFreeBooksByListId(@Param("ids") List<Long> ids);

  @Query("SELECT b FROM Book b LEFT JOIN b.authorList a LEFT JOIN b.genreList g "
      + "LEFT JOIN b.translatorList t LEFT JOIN b.reservList r "
      + "WHERE UPPER(b.name) LIKE %:name% AND "
      + "a.id IN :aids AND "
      + "g.id IN :gids AND "
      + "t.id IN :tids AND "
      + "r.reservationDateCancel > :date "
      + "GROUP BY b.id")
  List<Book> getReservByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
      Date date);


  @Query("SELECT b FROM Book b LEFT JOIN b.authorList a LEFT JOIN b.genreList g "
      + "LEFT JOIN b.translatorList t LEFT JOIN b.reservList r "
      + "WHERE UPPER(b.name) LIKE %:name% AND "
      + "a.id IN :aids AND "
      + "g.id IN :gids AND "
      + "t.id IN :tids AND "
      + "r.reservationDateCancel <= :date "
      + "GROUP BY b.id")
  List<Book> getFreeByParams(String name, List<Long> aids, List<Long> gids, List<Long> tids,
      Date date);

}
