package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
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
                .type(exerciseDTO.getType())
                .build();

        return exerciseRepository.save(exercise);
    }

    public Page<ExerciseDTO> findAll(PageRequest pageRequest) {
        return exerciseRepository.findAll(pageRequest)
                .map(this::buildDTO);
    }
    private ExerciseDTO buildExerciseDTO(Exercise exercise) {
        return  ExerciseDTO.builder()
                .name(exercise.getName())
                .duration(exercise.getDuration())
                .type(exercise.getType())
                .build();
    }
}
