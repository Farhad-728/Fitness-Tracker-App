package com.example.fitnesstrackerapp.controller;


import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.service.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/exercises")
@Tag(name = "exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        exerciseService.save(exerciseDTO);
    }
    @GetMapping("/find")
    private List<ExerciseDTO> findExercises() {
        return exerciseService.findAllExercises();
    }
}
