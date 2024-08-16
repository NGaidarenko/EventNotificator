package com.example.eventnotificator.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleException(Exception e) {
        ErrorMessageResponse exceptionMessageResponse = new ErrorMessageResponse(
                "Internal error",
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> entityNotFoundedException(EntityNotFoundException e) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                "Not founded",
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageResponse> illegalArgumentExceptionResponseEntity(IllegalArgumentException e) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                "Bad request",
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }
}
