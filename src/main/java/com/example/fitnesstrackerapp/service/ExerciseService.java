package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ExerciseService {

    Exercise save(ExerciseDTO exerciseDTO);
    Page<ExerciseDTO> findAll(PageRequest pageRequest);
}
