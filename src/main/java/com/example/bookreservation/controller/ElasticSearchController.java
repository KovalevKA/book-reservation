package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.service.elasticSearch.AbstractElasticSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
public class ElasticSearchController {

    @Autowired
    private AbstractElasticSearchService<BookDTO> elasticSearchService;

    @Operation(summary = "Search books", description = "search books in elastichsearch by keywords")
    @PostMapping("search")
    public List<BookDTO> search(
        @RequestParam
        @Parameter(description = "Key words for search books. Not be null")
            String keyWords
    ) throws Exception {
        return elasticSearchService.search(keyWords);
    }

}
