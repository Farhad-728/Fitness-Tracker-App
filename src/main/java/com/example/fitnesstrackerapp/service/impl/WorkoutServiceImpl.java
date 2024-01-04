package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.repository.ExerciseRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.repository.WorkoutRepository;
import com.example.fitnesstrackerapp.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public void save(WorkoutDTO workoutDTO, Long userId, Long exerciseId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User ID not found");
        }

        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        if (!exercise.isPresent()) {
            throw new UserNotFoundException("Exercise ID not found"); // create custom ExerciseNotFoundException
        }

        Workout workout = buildWorkout(workoutDTO, user.get(), exercise.get());
        workoutRepository.save(workout);
    }

    private Workout buildWorkout(WorkoutDTO workoutDTO, User user, Exercise exercise) {
        return Workout.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes((long) exercise.getDuration()))
                .type(workoutDTO.getType())
                .exercises(List.of(exercise))
                .user(user)
                .build();
    }
}
