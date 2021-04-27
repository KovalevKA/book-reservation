package com.example.bookreservation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "reserv_id"))
@Table(name = "reserv")
public class Reserv extends AbstractEntity {

    @Column(name = "reservation_date")
    private Date reservationDate;
    @Column(name = "reservation_date_cancel")
    private Date reservationDateCancel;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Book book;

    public Reserv (Client client, Book book, Date reservationDateCancel){
        this.book = book;
        book.addReserv(this);
        this.client = client;
        client.addReserv(this);
        this.reservationDate = new Date();
        this.reservationDateCancel = reservationDateCancel;
    }
}
