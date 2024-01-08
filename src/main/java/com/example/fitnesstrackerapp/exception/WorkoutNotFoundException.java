package com.example.fitnesstrackerapp.exception;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(String ex) {
        super(ex);
    }
}
