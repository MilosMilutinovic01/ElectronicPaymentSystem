package org.bankexample.bankbackend.advice;

import org.bankexample.bankbackend.exception.MerchantAlreadyExistsException;
import org.bankexample.bankbackend.exception.MerchantAuthFailedException;
import org.bankexample.bankbackend.exception.MerchantDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MerchantExceptionHandlerAdvice {

    @ExceptionHandler(MerchantAlreadyExistsException.class)
    public ResponseEntity<String> handleMerchantAlreadyExists(MerchantAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MerchantDoesNotExistException.class)
    public ResponseEntity<String> handleMerchantDoesNotExist(MerchantDoesNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MerchantAuthFailedException.class)
    public ResponseEntity<String> handleMerchantAuthFailed(MerchantAuthFailedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
