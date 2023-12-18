package com.example.fitnesstrackerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Timer;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressTracker {
    private int id;
    private  double weight;
    private Timer totalTime;
    private Date activeDays;
    private String exerciseList;
}
