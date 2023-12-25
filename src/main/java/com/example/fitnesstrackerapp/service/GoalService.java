package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.dto.UserDTO;

import java.util.List;

public interface GoalService {
    List<GoalDTO> getGoals();
}
