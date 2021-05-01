package com.example.bookreservation.controller;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("translators")
public class TranslatorController {

    @Autowired
    private TranslatorService translatorService;

    @GetMapping
    public ResponseEntity<List<TranslatorDTO>> getAllTranslators() {
        return ResponseEntity.ok(translatorService.getAll());
    }

    @PostMapping
    public ResponseEntity<TranslatorDTO> addTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return ResponseEntity.ok(translatorService.create(translatorDTO));
    }

    @GetMapping("find-name-like")
    public ResponseEntity<List<TranslatorDTO>> getTranslatorsByNameLike(
            @RequestParam(name = "name") String name) {
        return ResponseEntity.ok(translatorService.getTranslatorsByNameLike(name.toUpperCase()));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TranslatorDTO> editTranslator(
            @PathVariable Long id,
            @RequestBody TranslatorDTO translatorDTO
    ) {
        return ResponseEntity.ok(translatorService.editById(id, translatorDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTranslator(@PathVariable Long id) {
        translatorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
