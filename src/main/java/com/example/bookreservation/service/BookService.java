package com.example.bookreservation.service;

import static java.time.LocalDate.now;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.BookRepositoryQueries;
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
    private BookRepositoryQueries bookRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private TranslatorService translatorService;

    @Override
    public Mono<BookDTO> editById(Long id, BookDTO data) throws EntityNotFoundException {

        return bookRepository.findByBookId(id)
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

        return bookRepository
            .findByParams(
                isReserved,
                bookName,
                listAuthorId,
                listGenreId,
                listTranslatorsId,
                now()
            )
            .map(bookMapper::toDTO)
            .flatMap(bookDTO ->
                Mono.just(bookDTO)
                    .zipWith(authorService.getByBookId(bookDTO.getBookId())
                        .collectList()
                    )
                    .map(tupla -> {
                        tupla.getT1().setAuthors(tupla.getT2());
                        return tupla.getT1();
                    })
            ).flatMap(bookDTO ->
                Mono.just(bookDTO)
                    .zipWith(genreService.getByBookId(bookDTO.getBookId())
                        .collectList()
                    )
                    .map(tupla -> {
                        tupla.getT1().setGenres(tupla.getT2());
                        return tupla.getT1();
                    })
            ).flatMap(bookDTO ->
                Mono.just(bookDTO)
                    .zipWith(translatorService.getByBookId(bookDTO.getBookId())
                        .collectList()
                    )
                    .map(tupla -> {
                        tupla.getT1().setTranslators(tupla.getT2());
                        return tupla.getT1();
                    })
            );

    }

}