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

@Service
public class BookService extends AbstractServiceImpl<Book, BookDTO, BookRepository, BookMapper> {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private final BookMapper bookMapper;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private TranslatorService translatorService;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO create(String data) throws JsonProcessingException {
        BookDTO bookDTO = objectMapper.readValue(data, BookDTO.class);
        bookDTO.setAuthors((List<AuthorDTO>) getIdForNestedRecords(bookDTO.getAuthors(), authorService));
        bookDTO.setGenres((List<GenreDTO>) getIdForNestedRecords(bookDTO.getGenres(), genreService));
        bookDTO.setTranslators((List<TranslatorDTO>) getIdForNestedRecords(bookDTO.getTranslators(), translatorService));
        return bookMapper.toDTO(bookRepository.save(bookMapper.toEntity(bookDTO)));
    }

    public List<BookDTO> getFreeBooks() {
        return bookMapper.toDTOs(bookRepository.getAllFreeBooks());
    }

    private List<?> getIdForNestedRecords(List<? extends AbstractDTO> dataList, AbstractService service) {
        dataList.forEach(data -> {
            Long id = 0L;
            try {
                Field field = data.getClass().getDeclaredField("name");
                field.setAccessible(true);
                id = service.getByName((String) field.get(data)).getId();
                field.setAccessible(false);
            } catch (Exception e) {
                id = null;//service.create(data).getId();
            } finally {
                data.setId(id);
            }
        });
        return dataList;
    }
}
