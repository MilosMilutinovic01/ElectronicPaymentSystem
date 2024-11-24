package org.bankexample.bankbackend.advice;

import org.bankexample.bankbackend.exception.MerchantAlreadyExistsException;
import org.bankexample.bankbackend.exception.PaymentDoesNotExistException;
import org.bankexample.bankbackend.exception.PaymentParametersBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandlerAdvice {

    @ExceptionHandler(PaymentDoesNotExistException.class)
    public ResponseEntity<String> handlePaymentDoesNotExist(PaymentDoesNotExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentParametersBadRequestException.class)
    public ResponseEntity<String> handlePaymentParametersBadRequest(PaymentParametersBadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
