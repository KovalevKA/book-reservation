package com.example.bookreservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "book")
public class Book {

    @Id
    @Column("book_id")
    private Long bookId;
    @Column
    private String name;
    @Column("publishing_house")
    private String publishHouse;
    @Column("publishing_year")
    private int publishYear;
    @Column
    private String description;
}
