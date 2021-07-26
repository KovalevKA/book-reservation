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

    private String name =  "";
    private String publishHouse =  "";
    private Integer publishYear;
    private String authors =  "";
    private String genres =  "";
    private String translators =  "";

}
