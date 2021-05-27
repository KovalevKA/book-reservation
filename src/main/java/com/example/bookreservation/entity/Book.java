package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(value = "book")
public class Book extends AbstractEntity {

    @Transient
    private final List<Reserv> reservList = new ArrayList<>();
    @Transient
    private final Set<Author> authorList = new HashSet<>();
    @Transient
    private final Set<Genre> genreList = new HashSet<>();
    @Transient
    private final Set<Translator> translatorList = new HashSet<>();
    @Id
    @Column(name = "book_id")
    private Long bookId;
    @Column
    private String name;
    @Column
    private String publishHouse;
    @Column
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
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
