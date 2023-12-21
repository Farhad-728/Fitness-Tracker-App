package com.example.fitnesstrackerapp.dto;

import com.example.fitnesstrackerapp.enums.Gender;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private Double weight;
    private Gender gender;
}
