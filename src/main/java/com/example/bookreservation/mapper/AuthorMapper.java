package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import java.lang.reflect.Type;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements AbstractMapper<Author, AuthorDTO> {

    @Override
    public AuthorDTO toDTO(Author entity) {
        return mapper.map(entity, (Type) AuthorDTO.class);
    }

    @Override
    public Author toEntity(AuthorDTO dto) {
        return mapper.map(dto, (Type) Author.class);
    }
}
