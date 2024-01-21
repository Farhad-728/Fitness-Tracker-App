package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

public interface ExerciseService {

    Exercise save(ExerciseDTO exerciseDTO);
    Page<ExerciseDTO> findAll(PageRequest pageRequest);
}
