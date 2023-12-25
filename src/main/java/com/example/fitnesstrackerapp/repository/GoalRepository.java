package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
