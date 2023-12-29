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

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    private final UserRepository userRepository;

    @Override
    public void saveGoal(GoalDTO goalDTO, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            Goal newGoal = buildGoalObject(goalDTO, user.get());
            goalRepository.save(newGoal);
        } else {
            throw new UserNotFoundException("User not found to create the goal");
        }
    }

    @Override
    public void updateGoal(GoalDTO goalDTO) {
        Optional<Goal> goal = goalRepository.findById(goalDTO.getId());
        if(goal.isPresent()) {
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
    public  List<GoalDTO> getAllGoals() {
        List<Goal> goalList=goalRepository.getAll();
        List<GoalDTO> goalDTOList=new ArrayList<>();
        for(Goal g: goalList){
            goalDTOList.add(GoalDTO.builder()
                            .id(g.getId())
                            .name(g.getName())
                    .build());
        }
        return goalDTOList;
    }

    @Override
    @Transactional
    public void deleteGoalByUserId(Long id) {
        goalRepository.deleteGoalById(id);
    }
}
