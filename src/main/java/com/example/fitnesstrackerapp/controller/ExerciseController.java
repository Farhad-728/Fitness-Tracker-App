package com.example.fitnesstrackerapp.controller;


import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.service.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
