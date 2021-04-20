package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @PostMapping()
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

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getInfoAboutBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(null);
    }

}
