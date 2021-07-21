package com.example.bookreservation.dto.requestBodyParams;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParamsForSearchBooks {

    private Boolean isReserved = false;
    private String bookName = "";
    private List<Long> listGenreId = new ArrayList<>();
    private List<Long> listAuthorId = new ArrayList<>();
    private List<Long> listTranslatorsId = new ArrayList<>();

    public String getBookName() {
        return bookName.toUpperCase();
    }

}
