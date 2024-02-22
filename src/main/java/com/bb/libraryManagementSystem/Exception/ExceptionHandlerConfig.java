package com.bb.libraryManagementSystem.Exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Configuration
public class ExceptionHandlerConfig {

    @ExceptionHandler(TransactionServiceException.class)
    public ResponseEntity  handleTransactionServiceException(TransactionServiceException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
