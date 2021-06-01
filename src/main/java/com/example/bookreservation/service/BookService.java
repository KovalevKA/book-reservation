package com.example.bookreservation.service;

import static java.time.LocalDate.now;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.GenreRepository;
import com.example.bookreservation.repository.TranslatorRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService extends
    AbstractServiceImpl<Book, BookDTO, BookRepository, AbstractMapper<Book, BookDTO>> {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;

    @Override
    public Mono<BookDTO> editById(Long id, BookDTO data) throws EntityNotFoundException {

        return bookRepository.findById(id)
            .map(book -> {
                book.setName(data.getName());
                book.setPublishHouse(data.getPublishHouse());
                book.setPublishYear(data.getPublishYear());
                book.setDescription(data.getDescription());
                return book;
            })
            .flatMap(bookRepository::save)
            .map(bookMapper::toDTO);
    }

    public Flux<BookDTO> findByParams(Boolean isReserved, String bookName,
        List<Long> listGenreId, List<Long> listAuthorId, List<Long> listTranslatorsId) {

        Flux<Book> bookFlux = bookRepository
            .getFreeByParams(bookName, listAuthorId, listGenreId, listTranslatorsId, now());
        if (isReserved) {
            return bookFlux.concat(bookRepository
                .getReservByParams(bookName, listAuthorId, listGenreId, listTranslatorsId, now())
            )
                .map(bookMapper::toDTO);
        }
        return bookFlux.map(bookMapper::toDTO);
    }
}