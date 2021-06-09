package com.example.bookreservation.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservDTO {

    private Long reservId;
    private LocalDate reservationDate;
    private LocalDate reservationDateCancel;
    private BookDTO book;

}
