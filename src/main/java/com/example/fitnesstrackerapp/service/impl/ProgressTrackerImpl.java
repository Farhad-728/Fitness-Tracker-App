package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.entity.ProgressTracker;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.repository.WorkoutRepository;
import com.example.fitnesstrackerapp.service.ProgressTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressTrackerImpl implements ProgressTrackerService {

    private final WorkoutRepository workoutRepository;

    @Override
    public ProgressTracker getProgress(Long userId) {
        List<Workout> workouts = workoutRepository.findByUserId(userId);
        return ProgressTracker.builder()
                .totalTime(getTotalTime(workouts))
                .exerciseCount(getCompletedExerciseCount(workouts))
                .exercises(fetchCompletedExercises(workouts))
                .activeDays(getActiveDays(0, workouts))
                .build();
    }

    public long getTotalTime(List<Workout> workouts) {
        return workouts.stream()
                .mapToLong(workout -> Duration.between(workout.getStartTime(), workout.getEndTime())
                        .toMinutes())
                .sum();
    }

    public long getCompletedExerciseCount(List<Workout> workouts) {
        return workouts.stream()
                .flatMap(workout -> workout.getExercises().stream())
                .distinct()
                .count();
    }


    public List<Exercise> fetchCompletedExercises(List<Workout> workouts) {
        return workouts.stream()
                .flatMap(workout -> workout.getExercises().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public long getActiveDays(List<Workout> workouts) {
        return workouts.stream()
                .map(workout -> workout.getStartTime().truncatedTo(ChronoUnit.DAYS))
                .count();
    }
}

