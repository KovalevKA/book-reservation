package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(value = "client")
public class Client extends AbstractEntity {

    @Transient
    private final List<Reserv> reservList = new ArrayList<>();
    @Id
    @Column(name = "client_id")
    private Long clientId;
    @Column
    private String name;

    public void addReserv(Reserv reserv) {
        this.reservList.add(reserv);
    }
}
