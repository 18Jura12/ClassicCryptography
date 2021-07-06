package com.java.crypto.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<Object> handleNullPointer(NullPointerException ex) {

        log.error("Handling NullPointerException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {

        log.error("Handling MethodArgumentNotValidException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IOException.class)
    ResponseEntity<Object> handleIO(IOException ex) {

        log.error("Handling IOException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handle(Exception ex) {

        log.error("Handling Exception: " + ex.getLocalizedMessage() + "\n" + ex.toString());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

}
