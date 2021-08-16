package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.AbstractDTO;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.io.IOException;
import java.util.List;

public interface CommonElasticSearchService
        <DTO extends AbstractDTO> extends ApplicationListener<ApplicationReadyEvent> {

    List<DTO> search(String keyWords) throws Exception;

}
