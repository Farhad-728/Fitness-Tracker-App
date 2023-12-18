package com.example.fitnesstrackerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {
    private int id;
    private String target;
    private LocalDateTime deadline;
    private String status;
}
