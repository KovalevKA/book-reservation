package com.example.bookreservation.service;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.mapper.TranslatorMapper;
import com.example.bookreservation.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService extends AbstractServiceImpl<Translator, TranslatorDTO, TranslatorRepository, TranslatorMapper> {

    @Autowired
    private TranslatorRepository translatorRepository;

    public Translator getByName(String name) {
        return translatorRepository.getByName(name);
    }

}
