package com.example.project3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    // id: Generated automatically.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //accountNumber:
    @Column(columnDefinition = "varchar(40) not null")
    private String accountNumber;

    //balance:
    private Double balance;

    //isActive:
    private Boolean isActive;

   // relation
    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
