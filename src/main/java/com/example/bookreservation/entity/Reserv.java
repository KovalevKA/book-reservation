package com.example.bookreservation.entity;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "reserv")
public class Reserv {

    @Id
    @Column(value = "reserv_id")
    private Long reservId;
    @Column(value = "reservation_date")
    private LocalDate reservationDate;
    @Column(value = "reservation_date_cancel")
    private LocalDate reservationDateCancel;
    @Column(value = "client_id")
    private Long clientId;
    @Column(value = "book_id")
    private Long bookId;
    @Transient
    private Client client = new Client();
    private Book book = new Book();

    public Reserv(Long clientId, Long bookId, LocalDate reservationDateCancel) {
        this.clientId = clientId;
        this.bookId = bookId;
        this.reservationDate = now();
        this.reservationDateCancel = reservationDateCancel;
    }
}
