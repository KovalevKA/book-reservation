package com.example.bookreservation.controller;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Flux<AuthorDTO> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("find-name-like")
    public Flux<AuthorDTO> getAuthorByNameLike(
        @RequestParam(name = "name") String name) {
        return authorService.getByNameLike(name);
    }

    @PostMapping
    public Mono<AuthorDTO> addAuthor(@RequestBody AuthorDTO author) {
        return authorService.create(author);
    }

    @PatchMapping("{id}")
    public Mono<AuthorDTO> editAuthor(
        @PathVariable Long id,
        @RequestBody AuthorDTO author) {
        return authorService.editById(id, author);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
