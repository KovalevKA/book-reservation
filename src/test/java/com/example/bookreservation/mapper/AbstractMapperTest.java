package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMapperTest {

    AbstractMapper<Author, AuthorDTO> mapper = new AuthorMapper();

    @Test
    void toDTO() {
        AuthorDTO dto = new AuthorDTO();
        assertEquals(mapper.toDTO(new Author()), dto);
        dto.setName("testName");
        assertNotEquals(mapper.toDTO(new Author()), dto);
    }

    @Test
    void toEntity() {
        AuthorDTO dto = new AuthorDTO();
        assertEquals(dto, mapper.toDTO(new Author()));
        dto.setName("testName");
        assertNotEquals(dto, mapper.toDTO(new Author()));
    }

    @Test
    void toDTOs() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        authors.add(new Author("testName"));
        List<AuthorDTO> dtos = new ArrayList<>();
        dtos.add(new AuthorDTO());
        dtos.add(new AuthorDTO("testName"));
        assertEquals(mapper.toDTOs(authors), dtos);
        Collections.reverse(authors);
        assertNotEquals(mapper.toDTOs(authors), dtos);
    }

    @Test
    void toEntities() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        authors.add(new Author("testName"));
        List<AuthorDTO> dtos = new ArrayList<>();
        dtos.add(new AuthorDTO());
        dtos.add(new AuthorDTO("testName"));
        assertEquals(dtos, mapper.toDTOs(authors));
        Collections.reverse(authors);
        assertNotEquals(dtos, mapper.toDTOs(authors));
    }
}