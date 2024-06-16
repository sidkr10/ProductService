package org.sidkr.productservice.advices;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.sidkr.productservice.exceptions.ErrorDetails;
import org.sidkr.productservice.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ProductControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
        log.error("Resource not found",e);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 400,"Resource not found", e.getMessage(),request.getDescription(false).substring(4));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
