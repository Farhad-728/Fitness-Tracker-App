package com.example.fitnesstrackerapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private String name;
    private double duration;

}
