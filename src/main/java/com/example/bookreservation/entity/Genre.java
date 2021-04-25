package com.example.bookreservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "genre_id"))
@Table(name = "genre")
public class Genre extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "genre_book",
            joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        this.bookList.add(book);
        book.getGenreList().add(this);
    }

    public void removeBook(Book book) {
        this.bookList.remove(book);
        book.getGenreList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
