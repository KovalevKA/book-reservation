package com.example.bookreservation.controller.requestEntity;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MakeReservetionRequestParamEntity {

    @NotNull
    private Long clientId;
    @NotNull
    @Size(min = 1, max = 5)
    private List<Long> listBooksId;
    @NotNull
    @Future
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}")
    private LocalDate dateTo;

}
