package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService extends AbstractServiceImpl<Book, BookDTO, BookRepository, BookMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;

    @Transactional
    public BookDTO create(String data) throws JsonProcessingException {
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        return bookMapper.toDTO(bookRepository.saveAndFlush(bookMapper.toEntity(bookDTO)));
    }

    public BookDTO editById(Long id, String data) throws JsonProcessingException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        if (!bookDTO.getName().isEmpty()) book.setName(bookDTO.getName());
        if (!bookDTO.getPublishHouse().isEmpty()) book.setPublishHouse(bookDTO.getPublishHouse());
        if (bookDTO.getPublishYear() != 0) book.setPublishYear(bookDTO.getPublishYear());
        if (!bookDTO.getDescription().isEmpty()) book.setDescription(bookDTO.getDescription());
        return bookMapper.toDTO(bookRepository.saveAndFlush(book));
    }

    public List<BookDTO> finfBooksByParams(Boolean isReserved, String bookName, List<Long> listGenreId,
                                           List<Long> listAuthorId, List<Long> listTranslatorsId) {
        return null;
    }

    public List<BookDTO> getFreeBooks() {
        return bookMapper.toDTOs(bookRepository.getAllFreeBooks());
    }

}
