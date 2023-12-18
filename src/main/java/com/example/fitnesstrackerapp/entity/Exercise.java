package com.example.fitnesstrackerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    private int id;
    private String name;
    private double weight;
    private int duration;
}
