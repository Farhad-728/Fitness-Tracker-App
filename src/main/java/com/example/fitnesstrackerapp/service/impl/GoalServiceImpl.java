package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.entity.Goal;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.exception.GoalNotFoundException;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.repository.GoalRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    private final UserRepository userRepository;

    @Override
    public void saveGoal(GoalDTO goalDTO, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Goal newGoal = buildGoalObject(goalDTO, user.get());
            goalRepository.save(newGoal);
        } else {
            throw new UserNotFoundException("User not found to create the goal");
        }
    }

    @Override
    public void updateGoal(GoalDTO goalDTO) {
        Optional<Goal> goal = goalRepository.findById(goalDTO.getId());
        if (goal.isPresent()) {
            Goal updatedGoal = updateGoalObj(goalDTO, goal.get());
            goalRepository.save(updatedGoal);
        } else {
            throw new GoalNotFoundException("Goal not found to update");
        }
    }

    private Goal buildGoalObject(GoalDTO goalDTO, User user) {
        return Goal.builder()
                .name(goalDTO.getName())
                .target(goalDTO.getTarget())
                .goalType(goalDTO.getGoalType())
                .deadline(goalDTO.getDeadline())
                .archived(false)
                .user(user)
                .build();
    }

    private Goal updateGoalObj(GoalDTO goalDTO, Goal oldGoal) {
        return Goal.builder()
                .id(oldGoal.getId())
                .name(goalDTO.getName().equals(null) ? oldGoal.getName() : goalDTO.getName())
                .target(goalDTO.getTarget())
                .goalType(goalDTO.getGoalType())
                .deadline(goalDTO.getDeadline())
                .archived(false)
                .user(oldGoal.getUser())
                .build();
    }

    public List<GoalDTO> getAllGoals() {
        return goalRepository.getAll()
                .stream()
                .map(this::buildGoalDTO)
                .collect(Collectors.toList());
    }

    private GoalDTO buildGoalDTO(Goal g) {
        return GoalDTO.builder()
                .id(g.getId())
                .name(g.getName())
                .goalType(g.getGoalType())
                .target(g.getTarget())
                .build();
    }

    @Override
    public void deleteGoalById(Long id) {
        Optional<Goal> goal = goalRepository.findById(id);
        if (goal.isPresent()) {
            goalRepository.delete(goal.get());
        } else {
            throw new GoalNotFoundException("Goal not found to delete");
        }

    }
}
