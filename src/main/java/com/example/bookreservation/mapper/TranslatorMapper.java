package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import org.springframework.stereotype.Component;

@Component
public class TranslatorMapper implements AbstractMapper<Translator, TranslatorDTO> {

    @Override
    public TranslatorDTO toDTO(Translator translator) {
        TranslatorDTO translatorDTO = mapper.map(translator, TranslatorDTO.class);
        return translatorDTO;
    }

    @Override
    public Translator toEntity(TranslatorDTO translatorDTO) {
        Translator translator = mapper.map(translatorDTO, Translator.class);
        return translator;
    }
}
