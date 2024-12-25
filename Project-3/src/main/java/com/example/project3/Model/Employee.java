package com.example.project3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private Integer id;

    // position:
    private String position;

    //salary:
    private Double salary;

    // relation
    @OneToOne
    @MapsId
    private MyUser user;

}
