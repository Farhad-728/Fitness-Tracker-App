package com.example.fitnesstrackerapp.mock;

import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.entity.Workout;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Mock {

    public static Workout createWorkout(LocalDateTime startTime, LocalDateTime endTime) {
        Exercise exercise1 = new Exercise();
        exercise1.setId(1L);

        Exercise exercise2 = new Exercise();
        exercise2.setId(2L);

        Workout workout = new Workout();
        workout.setStartTime(startTime);
        workout.setEndTime(endTime);
        workout.setExercises(Arrays.asList(exercise1, exercise2));

        return workout;
    }

    public static List<Workout> getWorkouts() {
        return Arrays.asList
                (createWorkout(
                        LocalDateTime.now().minusMinutes(20),
                        LocalDateTime.now()),
                 createWorkout(
                         LocalDateTime.now().minusMinutes(25),
                         LocalDateTime.now()));
    }
}
