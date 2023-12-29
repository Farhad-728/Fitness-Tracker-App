package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.GoalDTO;

import java.util.List;

public interface GoalService {
    void saveGoal(GoalDTO goalDTO, Long userId);

    void updateGoal(GoalDTO goalDTO);

     List<GoalDTO> getAllGoals();

     void deleteGoalByUserId(Long id);
}
