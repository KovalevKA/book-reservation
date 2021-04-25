package com.example.bookreservation.service;

import com.example.bookreservation.dto.*;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

@Service
public class BookService extends AbstractServiceImpl<Book, BookDTO, BookRepository, BookMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private TranslatorService translatorService;


    public BookDTO create(String data) throws JsonProcessingException {
        Book book = bookRepository.save(new Book());
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        bookDTO.setId(book.getId());
        bookDTO.setAuthors((Set<AuthorDTO>) getIdForNestedRecords(bookDTO.getAuthors(), authorService));
        bookDTO.setGenres((Set<GenreDTO>) getIdForNestedRecords(bookDTO.getGenres(), genreService));
        bookDTO.setTranslators((Set<TranslatorDTO>) getIdForNestedRecords(bookDTO.getTranslators(), translatorService));
        book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public List<BookDTO> getFreeBooks() {
        return bookMapper.toDTOs(bookRepository.getAllFreeBooks());
    }

    private Set<?> getIdForNestedRecords(Set<? extends AbstractDTO> dataList, AbstractService service) {
        dataList.forEach(data -> {
            Long id = 0L;
            try {
                Field field = data.getClass().getDeclaredField("name");
                field.setAccessible(true);
                id = service.getByName((String) field.get(data)).getId();
                field.setAccessible(false);
            } catch (Exception e) {
                id = service.create(data).getId();
            } finally {
                data.setId(id);
            }
        });
        return dataList;
    }
}
