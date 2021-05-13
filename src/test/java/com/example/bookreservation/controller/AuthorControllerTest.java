package com.example.bookreservation.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bookreservation.dto.AuthorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  AuthorController authorController;

  ObjectMapper objectMapper = new ObjectMapper();
  String testName = "testName";

  @Test
  public void getAllAuthorsTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
        .andExpect(status().isOk());
  }

  @Test
  public void addAuthorTest() throws Exception {
    AuthorDTO authorDTO = new AuthorDTO();
    authorDTO.setName(testName);
    mockMvc.perform(MockMvcRequestBuilders.post("/authors")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(authorDTO)))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testName))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
  }

  @Test
  public void editAuthorTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/authors/0"))
        .andExpect(status().isBadRequest());
    AuthorDTO authorDTO = authorController.addAuthor(new AuthorDTO());
    String testNameForEdit = testName + authorDTO.getId();
    authorDTO.setName(testNameForEdit);
    mockMvc.perform(MockMvcRequestBuilders.patch(String.format("/authors/%d", authorDTO.getId()))
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(authorDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(testNameForEdit));
  }

  @Test
  public void deleteAuthorTest() throws Exception {
    Long id = authorController.addAuthor(new AuthorDTO()).getId();
    mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/authors/%d", id)))
        .andExpect(status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/authors/%d", id)))
        .andExpect(status().isBadRequest());
  }
}
