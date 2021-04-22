package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController extends ControllerExceptions {

    @Autowired
    private BookService bookService;

    @PostMapping("cearch")
    public ResponseEntity<List<BookDTO>> getBooksWhithParams(
            @RequestParam(name = "isReserved", defaultValue = "true") boolean isReserved,
            @RequestParam(name = "isFree", defaultValue = "true") boolean isFree,
            @RequestParam(name = "bookName", defaultValue = "") String bookName,
            @RequestParam(name = "listGenreId", defaultValue = "") List<Long> listGenreId,
            @RequestParam(name = "listAuthorId", defaultValue = "") List<Long> listAuthorId,
            @RequestParam(name = "listTranslatorsId", defaultValue = "") List<Long> listTranslatorsId
    ) {
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getInfoAboutBookByBookId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(null);
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
