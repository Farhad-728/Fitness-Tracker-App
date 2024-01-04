package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface WorkoutService {
    void save(WorkoutDTO workoutDTO, Long userId, Long exerciseId);
}
