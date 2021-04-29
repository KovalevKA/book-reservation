package com.example.bookreservation.controller;

import com.example.bookreservation.dto.GenreDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController extends ControllerExceptions {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @PostMapping
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.create(genreDTO));
    }

    @GetMapping("find-name-like")
    public ResponseEntity<List<GenreDTO>> getGenresByNameLike (
            @RequestParam (name = "name") String name){
        return ResponseEntity.ok(genreService.getGenresByNameLike(name.toUpperCase()));
    }

    @PatchMapping("{id}")
    public ResponseEntity<GenreDTO> editGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.editById(id, genreDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteGenre(@PathVariable Long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
