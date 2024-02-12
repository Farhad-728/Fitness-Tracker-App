package com.example.fitnesstrackerapp.dto;

import com.example.fitnesstrackerapp.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    @NotBlank
    @Size(min = 4, max = 20, message = "Size should between 5 and 20")
    private String username;
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    private String email;

    @NotEmpty
    private String password;
    private String name;

    @NotNull
    @Min(value = 18, message = "age should not be under 18")
    @Max(value = 100, message = "age should not be more than 100")
    private Integer age;

    private Double weight;
    private Gender gender;

    @NotBlank(message = "Role name must not be blank")
    private String roleName;
    private Boolean isEnabled;
}
