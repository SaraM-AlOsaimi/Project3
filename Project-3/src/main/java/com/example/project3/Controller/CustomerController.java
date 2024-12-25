package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.CustomerRegistrationDTO;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-system/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // register
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerRegistrationDTO customer){
        customerService.registerCustomer(customer);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Registered Successfully"));
    }

    // get customer
    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(customerService.getMyInfo(myUser.getId()));
    }

    // update customer
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal MyUser myUser , @RequestBody @Valid CustomerRegistrationDTO customer){
        customerService.updateCustomer(myUser.getId(),customer);
        return ResponseEntity.status(200).body(new ApiResponse("Updated Successfully"));
    }

    // delete customer
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@AuthenticationPrincipal MyUser myUser){
       customerService.deleteCustomer(myUser.getId());
       return ResponseEntity.status(200).body(new ApiResponse("Customer Deleted"));
    }

}
