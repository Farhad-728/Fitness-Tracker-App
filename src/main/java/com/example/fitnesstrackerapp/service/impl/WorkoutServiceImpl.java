package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.exception.ExerciseNotFoundException;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.exception.WorkoutNotFoundException;
import com.example.fitnesstrackerapp.repository.ExerciseRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.repository.WorkoutRepository;
import com.example.fitnesstrackerapp.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public void save(Long userId, Long exerciseId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User ID not found")
        );

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(
                () -> new ExerciseNotFoundException("Exercise ID not found")
        );

        Workout workout = buildEntity(user, exercise);
        workoutRepository.save(workout);
    }

    @Override
    public void update(Long workoutId, Long exerciseId) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(
                () -> new WorkoutNotFoundException("Workout not found")
        );

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(
                () -> new ExerciseNotFoundException("Exercise ID not found")
        );

        workout.getExercises().add(exercise);
        workout.setCaloriesBurned(calculateCaloriesBurned(workout.getExercises()));
        workoutRepository.save(workout);
    }


    private Workout buildEntity(User user, Exercise exercise) {
        return Workout.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes((long) exercise.getDuration()))
                .exercises(List.of(exercise))
                .caloriesBurned(calculateCaloriesBurned(List.of(exercise)))
                .user(user)
                .build();
    }

    public List<WorkoutDTO> findAll() {
        return workoutRepository.findAll()
                .stream()
                .map(this::buildDTO)
                .collect(Collectors.toList());
    }

    private WorkoutDTO buildDTO(Workout workout) {
        return WorkoutDTO.builder()
                .startTime(workout.getStartTime())
                .caloriesBurned(workout.getCaloriesBurned())
                .endTime(workout.getEndTime())
                .build();
    }

    private double calculateCaloriesBurned(List<Exercise> exercises) {
//        double totalColBurned =0.0;
//        for(Exercise e : exercises) {
//            totalColBurned += e.getType().getCalorieBurnIndex() * e.getDuration();
//        }

        return exercises.stream()
                .mapToDouble(e -> e.getType().getCalorieBurnIndex() * e.getDuration())
                .sum();

    }
}
