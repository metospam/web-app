package com.example.webapp.exception;

import lombok.Value;

@Value
public class UserAlreadyExistException extends RuntimeException{

    String exceptionMessage;
}
