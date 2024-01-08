package com.example.fitnesstrackerapp.exception;

public class ExerciseNotFoundException extends RuntimeException{
    public ExerciseNotFoundException(String ex) {
        super(ex);
    }
}
