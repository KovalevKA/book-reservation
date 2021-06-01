package com.example.bookreservation.controller.requestEntity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBooksWhithParamsRequestEntity {

    private Boolean isReserved = false;
    private String bookName = "";
    private List<Long> listGenreId = new ArrayList<>();
    private List<Long> listAuthorId = new ArrayList<>();
    private List<Long> listTranslatorsId = new ArrayList<>();

    public String getBookName() {
        return bookName.toUpperCase();
    }
    
    public List<Long> getListGenreId() {
        listGenreId.add(0L);
        return listGenreId;
    }

    public List<Long> getListAuthorId() {
        listAuthorId.add(0L);
        return listAuthorId;
    }

    public List<Long> getListTranslatorsId() {
        listTranslatorsId.add(0L);
        return listTranslatorsId;
    }
}
