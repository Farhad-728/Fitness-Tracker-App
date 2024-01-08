package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import jdk.dynalink.linker.LinkerServices;

import java.util.LinkedList;
import java.util.List;

public interface ExerciseService {

    Exercise save(ExerciseDTO exerciseDTO);

    List<ExerciseDTO> findAllExercises();
}
