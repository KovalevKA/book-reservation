package com.example.bookreservation.controller;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("translators")
public class TranslatorController {

    @Autowired
    private TranslatorService translatorService;

    @GetMapping
    public Flux<TranslatorDTO> getAllTranslators() {
        return translatorService.getAll();
    }

    @PostMapping
    public Mono<TranslatorDTO> addTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return translatorService.create(translatorDTO);
    }

    @GetMapping("find-by-name-like")
    public Flux<TranslatorDTO> getTranslatorsByNameLike(
        @RequestParam(name = "name") String name) {
        return translatorService.getByNameLike(name);
    }

    @PutMapping("{id}")
    public Mono<TranslatorDTO> editTranslator(
        @PathVariable Long id,
        @RequestBody TranslatorDTO translatorDTO) {
        return translatorService.editById(id, translatorDTO);
    }

    @DeleteMapping("{id}")
    public void deleteTranslator(@PathVariable Long id) {
        translatorService.deleteById(id);
    }
}
