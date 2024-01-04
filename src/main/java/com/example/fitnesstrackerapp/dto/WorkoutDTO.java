package com.example.fitnesstrackerapp.dto;

import com.example.fitnesstrackerapp.enums.WorkType;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double caloriesBurned;
    private WorkType type;
}
