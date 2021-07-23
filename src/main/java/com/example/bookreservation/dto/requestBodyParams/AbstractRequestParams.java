package com.example.bookreservation.dto.requestBodyParams;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractRequestParams {

    private String elIndex;
    private String elId;
    private List<String> keyWords = new ArrayList<>();

}
