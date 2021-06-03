package com.example.bookreservation.entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "client")
public class Client {

    @Id
    @Column(value = "client_id")
    private Long clientId;
    @Column
    private String name;
}
