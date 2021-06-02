package com.example.bookreservation.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "translator")
public class Translator {

    private List<Book> bookList = new ArrayList<>();
    @Id
    @Column(value = "translator_id")
    private Long translatorId;
    @Column
    private String name;

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    public void removeBook(Book book) {
        this.bookList.remove(book);
    }
}
