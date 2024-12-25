package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.DTO.EmployeeDTOOUT;
import com.example.project3.DTO.EmployeeRegistrationDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final MyUserRepository myUserRepository;
    private final EmployeeRepository employeeRepository;

    // register - employee
    public void registerEmployee(EmployeeRegistrationDTO employeeDTO){
        MyUser myUser = new MyUser();
        myUser.setId(null);
        myUser.setUsername(employeeDTO.getUsername());
        String hashedPass = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        myUser.setPassword(hashedPass);
        myUser.setName(employeeDTO.getName());
        myUser.setEmail(employeeDTO.getEmail());
        myUser.setRole("EMPLOYEE");
        myUserRepository.save(myUser);

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(myUser);
        employeeRepository.save(employee);
    }

    // get .. one Employee the logging one
    public EmployeeDTOOUT getMyInfo(Integer employee_id){
       MyUser myUser = myUserRepository.findMyUserById(employee_id);
       if(myUser==null){
           throw new ApiException("Wrong employee Id");
       }
       Employee employee = employeeRepository.findEmployeeByUser(myUser);
       EmployeeDTOOUT employeeDTOOUT = new EmployeeDTOOUT(myUser.getUsername(), myUser.getName(), myUser.getEmail(), employee.getPosition(),employee.getSalary());
       return employeeDTOOUT;
    }

    // update
    public void updateEmployee(Integer employee_id , EmployeeRegistrationDTO employeeDTO){
      MyUser myUser = myUserRepository.findMyUserById(employee_id);
      if(myUser==null){
         throw new ApiException("Wrong employee id");
      }
        myUser.setUsername(employeeDTO.getUsername());
        String hashedPass = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        myUser.setPassword(hashedPass);
        myUser.setName(employeeDTO.getName());
        myUser.setEmail(employeeDTO.getEmail());
        myUserRepository.save(myUser);

        Employee employee = employeeRepository.findEmployeeByUser(myUser);
        if (employee == null) {
            throw new ApiException("Employee record not found for the user");
        }
        employee.setUser(myUser);
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);
    }

    // delete
    public void deleteEmployee(Integer employee_id){
        MyUser myUser = myUserRepository.findMyUserById(employee_id);
        if(myUser==null){
            throw new ApiException("Wrong employee id");
        }
        myUserRepository.delete(myUser);
    }

}
