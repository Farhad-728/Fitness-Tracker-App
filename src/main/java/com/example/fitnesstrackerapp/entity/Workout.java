package com.example.fitnesstrackerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {
    private int id;
    private LocalDateTime localDateTime;
    private int duration;
    private double caloriesBurned;
    private String description;
}
