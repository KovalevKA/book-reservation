package com.example.bookreservation.controller;

import com.example.bookreservation.dto.GenreDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres (){
        return ResponseEntity.ok(null);
    }
}
