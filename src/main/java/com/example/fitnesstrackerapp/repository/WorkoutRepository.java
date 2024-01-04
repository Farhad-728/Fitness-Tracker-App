package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
