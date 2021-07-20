package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import java.util.List;
import java.util.Map;

public interface AbstractElasticSearchService
    <Entity extends AbstractEntity, DTO extends AbstractDTO> {

    List<DTO> getAll();

    DTO getById(Long id);

    boolean deleteById(Long id);

    DTO create(DTO dto);

    DTO editById(Long id, DTO dto);

    List<DTO> search(String title, Map<String, ?> params) throws Exception;

}
