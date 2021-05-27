package com.example.bookreservation.dto;

import lombok.Data;

@Data
public class TranslatorDTO extends AbstractDTO {

    private final String name;

    public TranslatorDTO(TranslatorDTO translatorDTO) {
        this.name = translatorDTO.name;
        this.setId(translatorDTO.getId());
    }
}
