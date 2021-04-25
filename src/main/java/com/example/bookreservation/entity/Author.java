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
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
@Table(name = "author")
public class Author extends AbstractEntity {

    @Column(name = "name")

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        this.bookList.add(book);
        book.getAuthorList().add(this);
    }

    public void removeBook(Book book) {
        this.bookList.remove(book);
        book.getAuthorList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
