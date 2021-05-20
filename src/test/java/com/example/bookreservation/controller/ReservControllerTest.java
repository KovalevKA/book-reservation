package com.example.bookreservation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ReservController reservController;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void getListReservByClientIdTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/reservation/0"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void checkReservedBooksByBookIdTest() {
  }

  @Test
  public void makeReservationTest() {
  }

  @Test
  public void cancelReservationTest() {
  }
}
