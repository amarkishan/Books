package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RuntimeException
{
    private HttpStatus status;
    public BookNotFoundException(String msg, HttpStatus status)
    {
        super(msg);
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
