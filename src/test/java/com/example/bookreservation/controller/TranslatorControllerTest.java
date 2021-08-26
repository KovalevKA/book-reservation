package com.example.bookreservation.controller;

import com.example.bookreservation.dto.TranslatorDTO;
import com.example.bookreservation.entity.Translator;
import com.example.bookreservation.service.TranslatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class TranslatorControllerTest {

    @Autowired
    private TranslatorService translatorService;

    @Autowired(required = false)
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void getAllTranslators() throws Exception {
        mockMvc.perform(get("/translators"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isNotEmpty());
    }

    @Test
    void addTranslator() throws Exception {
        Translator translator = new Translator();
        translator.setName("translator");
        String jsonObject = mapper.writeValueAsString(translator);
        mockMvc.perform(post("/translators")
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andExpect(status().isOk())
        ;
    }
    @Test
    void addEmptyTranslator() throws Exception {
        mockMvc.perform(post("/translators")
                .contentType(APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void getTranslatorsByNameLike() throws Exception {
        mockMvc.perform(get("/translators/find-name-like")
                .param("name", "translator"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void editTranslator() throws Exception {
        TranslatorDTO translatorDTO = new TranslatorDTO();
        translatorDTO.setName("test");
        translatorService.create(translatorDTO);
        String id = String.valueOf(translatorService.getByName("test").getId());
        translatorDTO.setId(id);
        translatorDTO.setName("test@");
        mockMvc.perform(patch(String.format("/translators/%s", id))
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(translatorDTO)))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void deleteTranslator() throws Exception {
        mockMvc.perform(delete(String.format("/translators/%s", 1)))
                .andExpect(status().isOk());
    }
}