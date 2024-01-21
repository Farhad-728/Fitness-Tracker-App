package com.example.fitnesstrackerapp.handler;

import com.example.fitnesstrackerapp.dto.FailureResponse;
import com.example.fitnesstrackerapp.exception.ExerciseNotFoundException;
import com.example.fitnesstrackerapp.exception.GoalNotFoundException;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.exception.WorkoutNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<FailureResponse> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
//        String defaultMessage = ex.getBindingResult().getFieldErrors()
//                .stream()
//                .map(f -> f.getDefaultMessage())
//                .collect(Collectors.joining(", "));
//        return buildFailureResponse(HttpStatus.BAD_REQUEST, defaultMessage);
//    }

    @ExceptionHandler({UserNotFoundException.class,
            GoalNotFoundException.class,
            ExerciseNotFoundException.class,
            WorkoutNotFoundException.class})
    public ResponseEntity<FailureResponse> handleNotFoundException(Exception ex) {
        return buildFailureResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<FailureResponse> buildFailureResponse(HttpStatus status, String errorDetail) {
        log.error("Exception occurred: {} ", errorDetail);
        FailureResponse response = FailureResponse.builder()
                .status(String.valueOf(status.value()))
                .error(status.name())
                .errorDetail(errorDetail)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}
