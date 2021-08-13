package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("es")
public class ElasticSearchController {

    @Operation(summary = "Search books", description = "search books in elastichsearch by keywords")
    @PostMapping("search")
    public List<BookDTO> search(
            @RequestParam
            @Parameter(description = "Key words for search books. Not be null") String keyWords) throws Exception {
        return null;
    }

}
