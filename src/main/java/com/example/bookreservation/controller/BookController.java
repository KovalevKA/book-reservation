package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController extends ControllerExceptions {

    @Autowired
    private BookService bookService;

    @PostMapping("search")
    public ResponseEntity<List<BookDTO>> getBooksWhithParams(
            @RequestParam(name = "isReserved", defaultValue = "true", required = false) boolean isReserved,
            @RequestParam(name = "isFree", defaultValue = "true", required = false) boolean isFree,
            @RequestParam(name = "bookName", defaultValue = "", required = false) String bookName,
            @RequestParam(name = "listGenreId", defaultValue = "", required = false) List<Long> listGenreId,
            @RequestParam(name = "listAuthorId", defaultValue = "", required = false) List<Long> listAuthorId,
            @RequestParam(name = "listTranslatorsId", defaultValue = "", required = false) List<Long> listTranslatorsId
    ) {
        return ResponseEntity.ok(bookService.getFreeBooks());
    }

    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody String data) throws JsonProcessingException {
        return ResponseEntity.ok(bookService.create(data));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getInfoAboutBookByBookId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<BookDTO> editBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
