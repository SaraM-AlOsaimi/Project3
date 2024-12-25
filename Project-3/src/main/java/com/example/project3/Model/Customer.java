package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private Integer id;

    // phoneNumber
    private String phoneNumber;

    // relation
    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    // One Customer May Have Many Accounts (OneToMany).
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
    private Set<Account> accounts;


}
