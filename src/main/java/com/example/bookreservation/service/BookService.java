package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import java.util.List;

public interface BookService<Entity extends AbstractEntity, DTO extends AbstractDTO>
    extends AbstractService<Entity, DTO> {

    List<DTO> findByParams(Boolean isReserved, String bookName,
        List<Long> listGenreId, List<Long> listAuthorId, List<Long> listTranslatorsId);
}
