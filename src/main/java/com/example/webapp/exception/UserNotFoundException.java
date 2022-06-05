package com.example.webapp.exception;

import lombok.Value;

@Value
public class UserNotFoundException extends RuntimeException {

    Long id;

}
