package com.example.bookreservation.entity;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(value = "reserv")
public class Reserv extends AbstractEntity {

    @Id
    @Column(name = "reserv_id")
    private Long reservId;
    @Column(name = "reservation_date")
    private final LocalDate reservationDate;
    @Column(name = "reservation_date_cancel")
    private final LocalDate reservationDateCancel;
    @Column(name = "client_id")
    private final Long clientId;
    @Column(name = "book_id")
    private final Long bookId;
    @Transient
    private final Client client = new Client();
    @Transient
    private final Book book = new Book();

    public Reserv(Long clientId, Long bookId, LocalDate reservationDateCancel) {
        this.clientId = clientId;
        this.bookId = bookId;
        this.reservationDate = now();
        this.reservationDateCancel = reservationDateCancel;
    }
}
