package com.example.bookreservation.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "reserv_id"))
@Table(name = "reserv")
public class Reserv extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client = new Client();
  @ManyToOne()
  @JoinColumn(name = "book_id")
  private Book book = new Book();
  @Column(name = "reservation_date")
  private Date reservationDate = new Date();
  @Column(name = "reservation_date_cancel")
  private Date reservationDateCancel = new Date();

  public Reserv(Client client, Book book, Date reservationDateCancel) {
    this.book = book;
    book.addReserv(this);
    this.client = client;
    client.addReserv(this);
    this.reservationDate = new Date();
    this.reservationDateCancel = reservationDateCancel;
  }
}
