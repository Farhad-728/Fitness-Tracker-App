package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
