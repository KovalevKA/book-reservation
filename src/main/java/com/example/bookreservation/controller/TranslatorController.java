package com.example.bookreservation.controller;

import com.example.bookreservation.dto.TranslatorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("translators")
public class TranslatorController {

    @GetMapping
    public ResponseEntity<List<TranslatorDTO>> getAllTranslators(){
        return ResponseEntity.ok(null);
    }

}
