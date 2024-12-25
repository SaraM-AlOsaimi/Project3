package com.example.project3.Service;

import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;




}
