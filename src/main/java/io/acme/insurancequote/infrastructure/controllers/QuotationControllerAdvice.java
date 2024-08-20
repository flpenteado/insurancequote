package io.acme.insurancequote.infrastructure.controllers;


import io.acme.insurancequote.domain.exception.DomainException;
import io.acme.insurancequote.infrastructure.controllers.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuotationControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleException(DomainException e){
        var response = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
