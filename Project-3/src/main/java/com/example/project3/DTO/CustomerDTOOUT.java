package com.example.project3.DTO;

import com.example.project3.Model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class CustomerDTOOUT {

    private String username;

    private String name;

    private String email;

    private String phoneNumber;

    private Set<Account> accounts;

}
