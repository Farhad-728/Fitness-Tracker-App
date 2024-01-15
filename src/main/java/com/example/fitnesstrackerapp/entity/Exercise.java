package com.example.fitnesstrackerapp.entity;

import com.example.fitnesstrackerapp.enums.ExerciseType;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double duration;
    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    @ManyToMany(mappedBy = "exercises")
    private Set<Workout> workouts;

}
