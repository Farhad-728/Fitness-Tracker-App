package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

public interface ExerciseService {

    Exercise save(ExerciseDTO exerciseDTO);

    Page<ExerciseDTO> findAll(PageRequest pageRequest);

    Page<ExerciseDTO> findByName(String name, PageRequest pageRequest);

    Page<ExerciseDTO> filterByType(ExerciseType type, PageRequest pageRequest);

    Page<ExerciseDTO> findByDuration(double duration, PageRequest pageRequest);

    Page<ExerciseDTO> findByMaxAndMin(double minDuration, double maxDuration, PageRequest pageRequest);

    Page<ExerciseDTO> findByTypeDuration(ExerciseType exerciseType, double minDuration, PageRequest pageRequest);
}
