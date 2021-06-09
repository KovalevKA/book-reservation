package com.example.bookreservation.dto.requestBodyParams;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestParamForCheckReservedBooksByBookId {

    @NotNull(message = "Can't be null")
    private List<Long> listBooksId;

}
