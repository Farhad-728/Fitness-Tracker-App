package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import com.example.fitnesstrackerapp.repository.ExerciseRepository;
import com.example.fitnesstrackerapp.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


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

    @Override
    public Page<ExerciseDTO> findByName(String name, PageRequest pageRequest) {
        Page<Exercise> exercisesByName = exerciseRepository.findByNameContainingIgnoreCase(name, pageRequest);
        return exercisesByName.map(this::buildDTO);
    }

    @Override
    public Page<ExerciseDTO> filterByType(ExerciseType type, PageRequest pageRequest) {
        Page<Exercise> exercisesByType = exerciseRepository.findByTypeOrderByDurationDesc(type, pageRequest);
        return exercisesByType.map(this::buildDTO);
    }

    @Override
    public Page<ExerciseDTO> findByDuration(double duration, PageRequest pageRequest) {
        Page<Exercise> exercisesByDuration = exerciseRepository.findByDurationGreaterThan(duration, pageRequest);
        return exercisesByDuration.map(this::buildDTO);
    }

    @Override
    public Page<ExerciseDTO> findByMaxAndMin(double minDuration, double maxDuration, PageRequest pageRequest) {
        Page<Exercise> exercisesByMaxAndMin = exerciseRepository.findByDurationBetweenMaxAndMin(minDuration, maxDuration, pageRequest);
        return exercisesByMaxAndMin.map(this::buildDTO);
    }

    public Page<ExerciseDTO> findAll(PageRequest pageRequest) {
        return exerciseRepository.findAll(pageRequest)
                .map(this::buildDTO);
    }

    private ExerciseDTO buildDTO(Exercise exercise) {
        return ExerciseDTO.builder()
                .name(exercise.getName())
                .duration(exercise.getDuration())
                .type(exercise.getType())
                .build();
    }
}
