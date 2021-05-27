package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("search")
    public Flux<BookDTO> getBooksWhithParams(
        @RequestParam(name = "isReserved", defaultValue = "false", required = false) boolean isReserved,
        @RequestParam(name = "bookName", defaultValue = "", required = false) String bookName,
        @RequestParam(name = "listGenreId", defaultValue = "", required = false) List<Long> listGenreId,
        @RequestParam(name = "listAuthorId", defaultValue = "", required = false) List<Long> listAuthorId,
        @RequestParam(name = "listTranslatorsId", defaultValue = "", required = false) List<Long> listTranslatorsId
    ) {
        return bookService
            .findByParams(isReserved, bookName.toUpperCase(), listGenreId, listAuthorId,
                listTranslatorsId);
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
