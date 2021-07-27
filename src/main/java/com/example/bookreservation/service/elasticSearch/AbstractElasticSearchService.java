package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.dto.requestBodyParams.AbstractRequestParams;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.rest.RestStatus;

public interface AbstractElasticSearchService
    <Params extends AbstractRequestParams, DTO extends AbstractDTO> {

    RestStatus add(Params params, DTO dto) throws Exception;

    RestStatus update(Params params, DTO dto) throws Exception;

    RestStatus delete(Params params) throws Exception;

    List<DTO> search(Params params) throws Exception;

    List<DTO> getWithPagination(Integer countInPage, Integer page) throws IOException;

}
