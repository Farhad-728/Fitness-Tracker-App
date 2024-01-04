package com.example.fitnesstrackerapp.controller;

import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import com.example.fitnesstrackerapp.service.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/workouts")
@Tag(name = "workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/save")
    public void createWorkout(@RequestBody WorkoutDTO workoutDTO,
                              @RequestParam Long userId,
                              @RequestParam Long exerciseId) {
        workoutService.save(workoutDTO, userId, exerciseId);
    }
}
