package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.dto.requestBodyParams.AbstractRequestParams;
import java.util.List;
import java.util.Map;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

public interface CommonElasticSearchService
    <Params extends AbstractRequestParams, DTO extends AbstractDTO> {

    RestStatus add(Params params, DTO dto) throws Exception;

    RestStatus update(Params params, DTO dto) throws Exception;

    RestStatus delete(Params params) throws Exception;

    List<DTO> search(Params params) throws Exception;

}