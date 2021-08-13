package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import org.elasticsearch.rest.RestStatus;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.io.IOException;
import java.util.List;

public interface CommonElasticSearchService
        <DTO extends AbstractDTO> extends ApplicationListener<ApplicationReadyEvent> {

    RestStatus add(DTO dto) throws Exception;

    RestStatus update(String id, DTO dto) throws Exception;

    RestStatus delete(String id) throws Exception;

    List<DTO> search(String keyWords) throws Exception;

    List<DTO> getWithPagination(Integer countInPage, Integer page) throws IOException;

}
