package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;

import java.util.List;

public interface AbstractService<Entity extends AbstractEntity, DTO extends AbstractDTO> {

    List<DTO> getAll();

    DTO getById(Long id);

    DTO getByName(String name);

    void deleteById(Long id);

    DTO create(DTO dto);

    DTO editById(Long id, DTO dto);

}
