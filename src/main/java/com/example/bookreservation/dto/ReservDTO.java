package com.example.bookreservation.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservDTO extends AbstractDTO {

    private Long reserv_id;
    private LocalDate reservationDate;
    private LocalDate reservationDateCancel;
    private BookDTO book;

}
