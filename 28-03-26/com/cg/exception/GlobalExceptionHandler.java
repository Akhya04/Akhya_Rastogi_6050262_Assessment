package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoanAmountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoanAmount(InvalidLoanAmountException ex) {
        ErrorResponse response = new ErrorResponse("InvalidLoanAmountException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateLoanApplicationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateApplication(DuplicateLoanApplicationException ex) {
        ErrorResponse response = new ErrorResponse("DuplicateLoanApplicationException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFound(LoanNotFoundException ex) {
        ErrorResponse response = new ErrorResponse("LoanNotFoundException", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Fallback for any other unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse response = new ErrorResponse("InternalServerError", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}