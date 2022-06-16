package com.example.webapp.exception;

import lombok.Value;

@Value
public class BookNotFoundException extends RuntimeException {
    Long id;
}
