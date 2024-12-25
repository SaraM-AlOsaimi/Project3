package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.EmployeeRegistrationDTO;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-system/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid EmployeeRegistrationDTO employee){
        employeeService.registerEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Employee registered Successfully"));
    }
    // get
    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(employeeService.getMyInfo(myUser.getId()));
    }

    // update
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid EmployeeRegistrationDTO employee){
       employeeService.updateEmployee(myUser.getId(),employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated Successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal MyUser myUser){
        employeeService.deleteEmployee(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }


}
