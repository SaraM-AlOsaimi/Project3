package com.example.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegistrationDTO {

    @NotEmpty(message = "username is required")
    @Size(min = 4 , max = 10 , message = "username length must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "password is required")
    @Size(min = 6 , message = "password length must be at least 6 characters")
    private String password;

    //name:
    @NotEmpty(message = "name is required")
    @Size(min = 2 ,max= 20, message = "name length must be between 2 and 20 characters.")
    private String name;

    //email:
    @NotBlank(message = "email is required")
    @Email(message = "please enter a valid email format")
    private String email;

    // employee attribute
    @NotEmpty(message = "position is required")
    private String position;

    @NotNull(message = "salary is required")
    @Positive(message = "salary must be a non-negative number")
    private Double salary;


}
