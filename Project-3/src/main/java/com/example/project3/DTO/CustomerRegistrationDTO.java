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
public class CustomerRegistrationDTO {

//    @NotEmpty(message = "username is required")
//    @Size(min = 4 , max = 10 , message = "username length must be between 4 and 10 characters")
    private String username;

//    @NotEmpty(message = "password is required")
//    @Size(min = 6 , message = "password length must be at least 6 characters")
    private String password;

    //name:
//    @NotEmpty(message = "name is required")
//    @Size(min = 2 ,max= 20, message = "name length must be between 2 and 20 characters.")
    private String name;

    //email:
//    @NotBlank(message = "email is required")
//    @Email(message = "please enter a valid email format")
    private String email;


    // customer attribute
//    @NotEmpty(message = "phoneNumber is required")
//    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be 10 digits long")
    private String phoneNumber;

}
