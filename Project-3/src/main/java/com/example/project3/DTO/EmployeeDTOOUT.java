package com.example.project3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTOOUT {

    private String username;

    private String name;

    private String email;

    private String position;

    private Double salary;
}
