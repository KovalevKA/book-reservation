package com.example.bookreservation.dto;

import lombok.Data;

@Data
public class BookDTO extends AbstractDTO {

    private String name;
    private String publishHouse;
    private int publishYear;
    private String description;

}
