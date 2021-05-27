package com.example.bookreservation.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerExceptions {

    /*@ExceptionHandler({EntityNotFoundException.class,
        IllegalArgumentException.class,
        MissingServletRequestParameterException.class,
        HttpRequestMethodNotSupportedException.class,
        JsonProcessingException.class,
        HttpMessageNotReadableException.class,
        IllegalAccessException.class,
        ParseException.class})
    public String exceptionHandler(Exception e) {
        return e.getMessage() != null ? e.getMessage() : e.getLocalizedMessage();
    }*/
}