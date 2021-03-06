package com.example.bookreservation.service;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.TranslatorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService extends
    AbstractServiceImpl<Translator, TranslatorDTO, TranslatorRepository, AbstractMapper<Translator, TranslatorDTO>> {

  @Autowired
  private TranslatorRepository translatorRepository;
  @Autowired
  private AbstractMapper<Translator, TranslatorDTO> translatorMapper;

  @Override
  public List<TranslatorDTO> getByNameLike(String name) {
    return translatorMapper
        .toDTOs(translatorRepository.findByNameContainsIgnoreCase(name));
  }

  @Override
  public Translator getByName(String name) {
    return translatorRepository.findByName(name);
  }
}