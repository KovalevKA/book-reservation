package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b " +
            "WHERE b.id NOT IN " +
            "(SELECT r.book.id FROM Reserv r WHERE r.reservationDateCancel > CURRENT_DATE) " +
            "GROUP BY b.id")
    List<Book> getAllFreeBooks();

    @Query("SELECT b FROM Book b " +
            "WHERE b.id NOT IN " +
            "(SELECT r.book.id FROM Reserv r WHERE r.reservationDateCancel > CURRENT_DATE) " +
            "AND b.id IN :ids " +
            "GROUP BY b.id")
    List<Book> getFreeBooksByListId(@Param("ids") List<Long> ids);

    @Query("SELECT b FROM Book b LEFT JOIN Reserv r ON b.id = r.book.id " +
            "WHERE r.reservationDateCancel > CURRENT_DATE " +
            "GROUP BY b.id")
    List<Book> getAllReservedBooks();

}
