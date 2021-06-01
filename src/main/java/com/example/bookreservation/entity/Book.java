package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "book")
public class Book extends AbstractEntity {

    @With
    private List<Reserv> reservList = new ArrayList<>();
    @With
    private Set<Author> authorList = new HashSet<>();
    @With
    private Set<Genre> genreList = new HashSet<>();
    @With
    private Set<Translator> translatorList = new HashSet<>();
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

    public void addAuthor(Author author) {
        this.authorList.add(author);
    }

    public void addGenre(Genre genre) {
        this.genreList.add(genre);
    }

    public void addTranslator(Translator translator) {
        this.translatorList.add(translator);
    }

    public void addReserv(Reserv reserv) {
        this.reservList.add(reserv);
    }
}
