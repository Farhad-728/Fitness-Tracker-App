package com.example.fitnesstrackerapp.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String ex) {
        super(ex);
    }
}
