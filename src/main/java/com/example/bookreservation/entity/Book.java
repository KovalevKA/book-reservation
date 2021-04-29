package com.example.bookreservation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
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
    private List<Reserv> reservList = new ArrayList<>();

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Author> authorList = new HashSet<>();

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Genre> genreList = new HashSet<>();

    @ManyToMany(mappedBy = "bookList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Translator> translatorList = new HashSet<>();

    public void addAuthors(Set<Author> authors) {
        this.authorList.addAll(authors);
    }

    public void addGenres(Set<Genre> genres) {
        this.genreList.addAll(genres);
    }

    public void addTranslators(Set<Translator> translators) {
        this.translatorList.addAll(translators);
    }

    public void addReserv (Reserv reserv){
        this.reservList.add(reserv);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return publishYear == book.publishYear &&
                name.equals(book.name) &&
                publishHouse.equals(book.publishHouse) &&
                description.equals(book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, publishHouse, publishYear, description);
    }
}
