package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TranslatorMapper implements AbstractMapper<Translator, TranslatorDTO> {

    private ModelMapper mapper = new ModelMapper();

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
