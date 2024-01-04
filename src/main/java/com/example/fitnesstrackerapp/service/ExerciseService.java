package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;

public interface ExerciseService {

    Exercise save(ExerciseDTO exerciseDTO);
}
