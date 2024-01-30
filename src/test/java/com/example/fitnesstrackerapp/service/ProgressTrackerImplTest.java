package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.entity.ProgressTracker;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.repository.WorkoutRepository;
import com.example.fitnesstrackerapp.service.impl.ProgressTrackerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static com.example.fitnesstrackerapp.mock.Mock.getWorkouts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgressTrackerImplTest {

    @InjectMocks
    private ProgressTrackerImpl progressTrackerImpl;
    @Mock
    private WorkoutRepository workoutRepository;

    @Test
    void testGetProgress() {
        List<Workout> workouts = getWorkouts();
        when(workoutRepository.findByUserId(any())).thenReturn(workouts);
        ProgressTracker progressTracker = progressTrackerImpl.getProgress(1L, 0L);
        assertEquals(45, progressTracker.getTotalTime());
        assertEquals(2, progressTracker.getExerciseCount());
    }

    @Test
    void testGetProgressWithDayFilter() {
        List<Workout> workouts = getWorkouts();
        when(workoutRepository.findAllByDays(any(), any())).thenReturn(workouts);
        ProgressTracker progressTracker = progressTrackerImpl.getProgress(1L, 7L);
        assertEquals(45, progressTracker.getTotalTime());
        assertEquals(2, progressTracker.getExerciseCount());
    }
}
