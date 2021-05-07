package com.example.bookreservation.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.text.ParseException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptions {

  @ExceptionHandler({EntityNotFoundException.class,
      IllegalArgumentException.class,
      MissingServletRequestParameterException.class,
      HttpRequestMethodNotSupportedException.class,
      JsonProcessingException.class,
      HttpMessageNotReadableException.class,
      IllegalAccessException.class,
      ParseException.class})
  public String exceptionHandler(Exception e) {
    return e.getMessage() != null ? e.getMessage() : e.getLocalizedMessage();
  }
}