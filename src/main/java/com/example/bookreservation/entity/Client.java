package com.example.bookreservation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@Table(name = "client")
public class Client extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Reserv> reservList;
}
