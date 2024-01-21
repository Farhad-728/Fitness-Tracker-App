package com.example.fitnesstrackerapp.dto;
import com.example.fitnesstrackerapp.enums.GoalType;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {
    private Long id;
    private String name;
    private String target;
    private GoalType goalType;
    private LocalDate deadline;
    private boolean archived;
}
