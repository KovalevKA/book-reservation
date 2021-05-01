package com.example.bookreservation.service;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.mapper.TranslatorMapper;
import com.example.bookreservation.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorService extends AbstractServiceImpl<Translator, TranslatorDTO, TranslatorRepository, TranslatorMapper> {

    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Translator, TranslatorDTO> translatorMapper;

    public List<TranslatorDTO> getTranslatorsByNameLike(String name) {
        return translatorMapper.toDTOs(translatorRepository.getTranslatorsByNameLike(name));
    }

    @Override
    public Translator getByName(String name) {
        return translatorRepository.getByName(name);
    }
}
