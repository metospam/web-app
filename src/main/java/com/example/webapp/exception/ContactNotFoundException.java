package com.example.webapp.exception;

import lombok.Value;

@Value
public class ContactNotFoundException extends RuntimeException {

    Long id;

}
