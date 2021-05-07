package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@Table(name = "client")
public class Client extends AbstractEntity {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
  private final List<Reserv> reservList = new ArrayList<>();
  @Column(name = "name")
  private String name;

  public void addReserv(Reserv reserv) {
    this.reservList.add(reserv);
  }
}
