package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.requestBodyParams.RequestBookSearchParam;
import com.example.bookreservation.service.elasticSearch.AbstractElasticSearchService;
import java.util.List;
import org.elasticsearch.rest.RestStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
public class ElasticSearchController {

    private AbstractElasticSearchService<RequestBookSearchParam, BookDTO> elasticSearchService;

    public ElasticSearchController(
        AbstractElasticSearchService<RequestBookSearchParam, BookDTO> elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @PostMapping("search")
    public List<BookDTO> search(@RequestBody RequestBookSearchParam params) throws Exception {
        return elasticSearchService.search(params);
    }

    @PostMapping("add")
    public RestStatus add(
        @RequestBody BookDTO dto) throws Exception {
        RequestBookSearchParam params = new RequestBookSearchParam();
        params.setElIndex("books");
        return elasticSearchService.add(params, dto);
    }

    @PostMapping("delete/{id}")
    public RestStatus delete(@PathVariable String id) throws Exception {
        RequestBookSearchParam params = new RequestBookSearchParam();
        params.setElIndex("books");
        params.setElId(id);
        return elasticSearchService.delete(params);
    }

    @PostMapping(value = "update/{id}")
    public RestStatus update(@PathVariable String id,
        @RequestBody BookDTO dto) throws Exception {
        RequestBookSearchParam params = new RequestBookSearchParam();
        params.setElIndex("books");
        params.setElId(id);
        return elasticSearchService.update(params, dto);
    }

}
