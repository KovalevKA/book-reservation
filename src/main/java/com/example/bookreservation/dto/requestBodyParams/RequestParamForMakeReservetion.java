package com.example.bookreservation.dto.requestBodyParams;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestParamForMakeReservetion {

    @NotNull
    private Long clientId;

    @NotNull
    @Size(max = 5, min = 1)
    private List<Long> listBooksId;

    @NotNull
    @Future
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Date format must be YYYY-MM-DD")
    private LocalDate dateTo;
}
