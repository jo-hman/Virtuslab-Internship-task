package com.virtuslab.internship.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    record ErrorDetails(Date date,
                        HttpStatus httpStatus,
                        String exceptionMessage,
                        String servletPath ){}

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetails> handleNoSuchElementException(NoSuchElementException noSuchElementException, HttpServletRequest httpServletRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                HttpStatus.NOT_FOUND,
                noSuchElementException.getMessage(),
                httpServletRequest.getServletPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<ErrorDetails> handleIndexOutOfBoundsException(IndexOutOfBoundsException indexOutOfBoundsException, HttpServletRequest httpServletRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                HttpStatus.NOT_FOUND,
                indexOutOfBoundsException.getMessage(),
                httpServletRequest.getServletPath());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
