package com.example.bookreservation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "genre_id"))
@Table(name = "genre")
public class Genre extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "genre_book",
            joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    private List<Book> bookList;


}
