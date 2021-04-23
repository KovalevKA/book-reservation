package com.example.bookreservation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
@Table(name = "book")
public class Book extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "publishing_house")
    private String publishHouse;
    @Column(name = "publishing_year")
    private int publishYear;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Reserv> reservList;

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Author> authorList;

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Genre> genreList;

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Translator> translatorList;
}
