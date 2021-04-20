package com.example.bookreservation.controller;

import com.example.bookreservation.dto.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        return ResponseEntity.ok(null);
    }
}
