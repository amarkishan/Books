package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler
{

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleCustomException(BookNotFoundException ex)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("message",ex.getMessage());
        response.put("status",ex.getStatus().value());
        return  new ResponseEntity<>(response,ex.getStatus());

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGenericException(Exception ex)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("message",ex.getMessage());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return  new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
