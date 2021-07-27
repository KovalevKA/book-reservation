package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
@Table(name = "book")
public class Book extends AbstractEntity {

  public static final String INDEX = "books";

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
  private final List<Reserv> reservList = new ArrayList<>();
  @ManyToMany(mappedBy = "bookList",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Author> authorList = new HashSet<>();
  @ManyToMany(mappedBy = "bookList",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Genre> genreList = new HashSet<>();
  @ManyToMany(mappedBy = "bookList",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Translator> translatorList = new HashSet<>();
  @Column(name = "name")
  private String name;
  @Column(name = "publishing_house")
  private String publishHouse;
  @Column(name = "publishing_year")
  private int publishYear;
  @Column(name = "description")
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
