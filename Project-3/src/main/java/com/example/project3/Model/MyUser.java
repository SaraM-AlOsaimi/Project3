package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {

    // id: Generated automatically.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //username:
    @Column(columnDefinition = "varchar(10) unique")
    private String username;


    //password:
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    //name:
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    //email:
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    //role:
    //Must be either "CUSTOMER" , "EMPLOYEE" or "ADMIN" only.
    @Pattern(regexp = "^CUSTOMER|EMPLOYEE|ADMIN$")
    private String role;

    // relation :
    // User can be an employee or a customer (OneToOne).
    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
