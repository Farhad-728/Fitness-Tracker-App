package com.example.fitnesstrackerapp.dto;

import com.example.fitnesstrackerapp.enums.Gender;
import com.example.fitnesstrackerapp.enums.GoalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {
    private String name;
    private String target;
    private GoalType goalType;
    private LocalDate deadline;
    private boolean archived;

}
