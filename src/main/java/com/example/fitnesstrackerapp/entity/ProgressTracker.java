package com.example.fitnesstrackerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressTracker {
    private double weight;
    private long totalTime;
    private long exerciseCount;
    private long activeDays;

    private List<Exercise> exercises;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitness_user_id")
    private User user;



    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime truncatedToDays = dateTime.truncatedTo(ChronoUnit.DAYS);

        System.out.println("Original: " + dateTime);
        System.out.println("Truncated to days: " + truncatedToDays);
    }

    //getTotalTime - minutes
    //getCompletedExercisesCount - long
    //getCompletedExercises - list of exercises
    //getActiveDays (all, last 7 days, last 30 days) - long
}
