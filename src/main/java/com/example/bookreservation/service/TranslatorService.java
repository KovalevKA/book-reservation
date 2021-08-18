package com.example.bookreservation.service;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorService extends
        CommonServiceImpl<Translator, TranslatorDTO, TranslatorRepository, AbstractMapper<Translator, TranslatorDTO>> {

  @Autowired
  private TranslatorRepository translatorRepository;
  @Autowired
  private AbstractMapper<Translator, TranslatorDTO> translatorMapper;

  public TranslatorService() {
    super();
    setClazz(Translator.class);
  }

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