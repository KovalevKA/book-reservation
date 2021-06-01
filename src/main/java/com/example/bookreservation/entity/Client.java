package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Table(value = "client")
public class Client extends AbstractEntity {

    private List<Reserv> reservList = new ArrayList<>();
    @Id
    @Column(value = "client_id")
    private Long clientId;
    @Column
    private String name;

    public void addReserv(Reserv reserv) {
        this.reservList.add(reserv);
    }
}
