package com.example.fitnesstrackerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FailureResponse {
    private String status;
    private String error;
    private String errorDetail;
    private String path;
    private LocalDateTime timestamp;
}
