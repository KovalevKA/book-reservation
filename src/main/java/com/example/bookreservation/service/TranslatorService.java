package com.example.bookreservation.service;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TranslatorService extends
    AbstractServiceImpl<Translator, TranslatorDTO, TranslatorRepository, AbstractMapper<Translator, TranslatorDTO>> {

    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Translator, TranslatorDTO> translatorMapper;

    @Override
    public Flux<TranslatorDTO> getByNameLike(String name) {
        return translatorRepository.findByNameContainsIgnoreCase(name)
            .map(translator -> new TranslatorDTO(translatorMapper.toDTO(translator)));
    }

    @Override
    public Mono<Translator> getByName(String name) {
        return translatorRepository.findByName(name);
    }
}