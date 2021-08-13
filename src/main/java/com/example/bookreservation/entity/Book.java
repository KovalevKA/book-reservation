package com.example.bookreservation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Indexed
@Entity
@Table(name = "book")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends AbstractEntity {

  public static final String INDEX = "books";

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
  private final List<Reserv> reservList = new ArrayList<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.LAZY,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Author> authorList = new HashSet<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.LAZY,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Genre> genreList = new HashSet<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.LAZY,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Translator> translatorList = new HashSet<>();

  @FullTextField
  @Column(name = "name")
  private String name;

  @FullTextField
  @Column(name = "publishing_house")
  private String publishHouse;

  @GenericField
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
