package com.example.bookreservation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
@Table(name = "author")
public class Author extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book> bookList;

}
