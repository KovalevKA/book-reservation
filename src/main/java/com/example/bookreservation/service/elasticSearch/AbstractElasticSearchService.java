package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.rest.RestStatus;

public interface AbstractElasticSearchService
    <DTO extends AbstractDTO> {

    RestStatus add(DTO dto) throws Exception;

    RestStatus update(String id, DTO dto) throws Exception;

    RestStatus delete(String id) throws Exception;

    List<DTO> search(String keyWords) throws Exception;

    List<DTO> getWithPagination(Integer countInPage, Integer page) throws IOException;

}
