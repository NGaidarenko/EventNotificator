package com.example.eventnotificator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        ErrorMessageResponse exceptionMessageResponse = new ErrorMessageResponse(
                "Internal error",
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
