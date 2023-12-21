package com.example.fitnesstrackerapp.entity;

import com.example.fitnesstrackerapp.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private Gender gender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitness_user_id")
    private User user;
}

