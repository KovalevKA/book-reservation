package com.example.bookreservation.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Entity not found" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Patch error" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Missimg parameter" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Missimg parameter" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> jsonProcessingException(JsonProcessingException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Error input data" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Error input data" : e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> illegalAccessException(IllegalAccessException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Error access" : e.getMessage())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> parseException(ParseException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage() == null ? "Error convert" : e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @Data
    @AllArgsConstructor
    class ErrorMessage {
        private String message;
    }

}
