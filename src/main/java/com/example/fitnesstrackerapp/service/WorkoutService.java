package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import java.util.List;

public interface WorkoutService {
    void save(Long userId, Long exerciseId);
    void update(Long workoutId, Long exerciseId);

    List<WorkoutDTO> findAll();
}
