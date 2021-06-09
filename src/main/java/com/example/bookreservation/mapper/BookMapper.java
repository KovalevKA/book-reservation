package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements AbstractMapper<Book, BookDTO> {

    @Override
    public BookDTO toDTO(Book book) {
        return mapper.map(book, BookDTO.class);
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

}
