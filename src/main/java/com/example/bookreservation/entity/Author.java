package com.example.bookreservation.entity;

import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
@Table(name = "author")
public class Author extends AbstractEntity {

  @ManyToMany(fetch = FetchType.LAZY,
          targetEntity = Book.class,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "author_book",
          joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")},
          inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
  private final List<Book> bookList = new ArrayList<>();

  @FullTextField
  @Column(name = "name")
  private String name;

  public void addBook(Book book) {
    this.bookList.add(book);
  }

  public void removeBook(Book book) {
    this.bookList.remove(book);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Author)) {
      return false;
    }
    Author author = (Author) o;
    return name.equals(author.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
