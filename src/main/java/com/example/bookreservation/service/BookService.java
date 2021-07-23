package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.requestBodyParams.RequestBookSearchParam;
import com.example.bookreservation.entity.AbstractEntity;
import java.util.List;

public interface BookService<Entity extends AbstractEntity, DTO extends AbstractDTO>
    extends AbstractService<Entity, DTO>, AbstractElasticSearchService<RequestBookSearchParam, BookDTO> {

    List<DTO> findByParams(Boolean isReserved, String bookName,
        List<Long> listGenreId, List<Long> listAuthorId, List<Long> listTranslatorsId);

}