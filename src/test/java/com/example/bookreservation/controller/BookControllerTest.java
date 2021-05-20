package com.example.bookreservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  BookController bookController;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void getBooksWhithParamsTest() {
  }

  @Test
  public void editBookTest() {
  }
}
