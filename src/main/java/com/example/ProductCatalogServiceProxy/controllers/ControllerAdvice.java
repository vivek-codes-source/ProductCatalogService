package com.example.ProductCatalogServiceProxy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerAdvice {
    @ExceptionHandler({IllegalArgumentException.class})
    private ResponseEntity<String> handleException() {
        return new ResponseEntity<String>("kuch toh phata hai", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
