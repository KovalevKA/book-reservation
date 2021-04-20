package com.example.bookreservation.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservDTO extends AbstractDTO{

    private Date reservationDate;
    private Date reservationDateCancel;
    private List<BookDTO> bookList;
    private List<ClientDTO> clientList;

}
