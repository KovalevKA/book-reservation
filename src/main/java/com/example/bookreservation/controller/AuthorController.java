package com.example.bookreservation.controller;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController extends ControllerExceptions {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO author) {
        return ResponseEntity.ok(authorService.create(author));
    }

    @PatchMapping("{id}")
    public ResponseEntity<AuthorDTO> editAuthor(@PathVariable Long id, @RequestBody AuthorDTO author) {
        return ResponseEntity.ok(authorService.editById(id, author));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
