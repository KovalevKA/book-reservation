package com.example.bookreservation.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.text.ParseException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler({EntityNotFoundException.class,
        IllegalArgumentException.class,
        JsonProcessingException.class,
        HttpMessageNotReadableException.class,
        IllegalAccessException.class,
        ParseException.class,
        InvalidDataAccessApiUsageException.class,
        EntityExistsException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e) {
        return e.getMessage() != null ? e.getMessage() : e.getLocalizedMessage();
    }
}