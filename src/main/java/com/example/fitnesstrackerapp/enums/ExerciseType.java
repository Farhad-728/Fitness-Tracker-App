package com.example.fitnesstrackerapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseType {
    AEROBIC(5.0),
    CARDIO(6.0),
    STRENGTH(4.0),
    RUN(7.0),
    STRETCHING(3.0);

    private final double calorieBurnIndex;
}
