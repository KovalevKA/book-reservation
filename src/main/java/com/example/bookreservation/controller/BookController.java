package com.example.bookreservation.controller;

import com.example.bookreservation.controller.requestEntity.GetBooksWhithParamsRequestEntity;
import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Flux<Book> getAll(){return bookRepository.findAll();}

    @PostMapping("search")
    public Flux<BookDTO> getBooksWhithParams(
        @RequestBody GetBooksWhithParamsRequestEntity requestParams) {
        return bookService
            .findByParams(requestParams.isReserved(), requestParams.getBookName(),
                requestParams.getListGenreId(), requestParams.getListAuthorId(),
                requestParams.getListTranslatorsId());
    }

    @PostMapping()
    public Mono<BookDTO> addBook(@RequestBody BookDTO data) {
        return bookService.create(data);
    }

    @GetMapping("{id}")
    public Mono<BookDTO> getInfoAboutBookByBookId(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping("{id}")
    public Mono<BookDTO> editBook(@PathVariable("id") Long id, @RequestBody BookDTO data) {
        return bookService.editById(id, data);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
