package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService extends AbstractServiceImpl<Book, BookDTO, BookRepository, BookMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Transactional
    public BookDTO create(String data) throws JsonProcessingException {
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        return bookMapper.toDTO(bookRepository.saveAndFlush(bookMapper.toEntity(bookDTO)));
    }

    /**
     * TODO : ''
     * */
    public BookDTO editById(Long id, String data) throws JsonProcessingException, IllegalAccessException {
        //Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        throw new IllegalAccessException("Don't work now");
    }

    public List<BookDTO> getFreeBooks() {
        return bookMapper.toDTOs(bookRepository.getAllFreeBooks());
    }

}
