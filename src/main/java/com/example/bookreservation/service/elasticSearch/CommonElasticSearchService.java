package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.List;

public interface CommonElasticSearchService
        <DTO extends AbstractDTO> {

    RestStatus add(DTO dto) throws Exception;

    RestStatus update(String id, DTO dto) throws Exception;

    RestStatus delete(String id) throws Exception;

    List<DTO> search(String keyWords) throws Exception;

    List<DTO> getWithPagination(Integer countInPage, Integer page) throws IOException;

}
