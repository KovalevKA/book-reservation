package com.example.bookreservation.dto.requestBodyParams;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookSearchParam extends AbstractRequestParams {

    private List<String> name = new ArrayList<>();
    private List<String> publishHouse = new ArrayList<>();
    private List<Integer> publishYear = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<String> translators = new ArrayList<>();

}
