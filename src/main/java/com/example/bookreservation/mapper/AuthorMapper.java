package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import java.lang.reflect.Type;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements AbstractMapper<Author, AuthorDTO> {

    @Override
    public AuthorDTO toDTO(Author entity) {
        AuthorDTO dto = mapper.map(entity, (Type) AuthorDTO.class);
        return dto;
    }

    @Override
    public Author toEntity(AuthorDTO dto) {
        Author entity = mapper.map(dto, (Type) Author.class);
        return entity;
    }
}
