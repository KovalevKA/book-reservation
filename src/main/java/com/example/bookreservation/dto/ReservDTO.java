package com.example.bookreservation.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ReservDTO extends AbstractDTO {

  private Date reservationDate;
  private Date reservationDateCancel;
  private BookDTO book;
}
