package com.example.bookreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorDTO extends AbstractDTO {

    private String name;

    public TranslatorDTO(TranslatorDTO translatorDTO) {
        this.name = translatorDTO.name;
        this.setId(translatorDTO.getId());
    }
}
