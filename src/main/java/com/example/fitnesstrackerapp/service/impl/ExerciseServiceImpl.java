package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.repository.ExerciseRepository;
import com.example.fitnesstrackerapp.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Override
    public Exercise save(ExerciseDTO exerciseDTO) {
        Exercise exercise = Exercise.builder()
                .name(exerciseDTO.getName())
                .duration(exerciseDTO.getDuration())
                .build();

        return exerciseRepository.save(exercise);
    }
    public List<ExerciseDTO> findAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::buildExerciseDTO)
                .collect(Collectors.toList());
    }
    private ExerciseDTO buildExerciseDTO(Exercise exercise) {
        return  ExerciseDTO.builder()
                .name(exercise.getName())
                .build();
    }
}
