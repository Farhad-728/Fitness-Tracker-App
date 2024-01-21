package com.example.fitnesstrackerapp.dto;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private String name;
    private double duration;
    private ExerciseType type;

}
