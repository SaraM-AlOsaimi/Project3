package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.DTO.CustomerDTOOUT;
import com.example.project3.DTO.CustomerRegistrationDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;


    // Register - Customer
    public void registerCustomer(CustomerRegistrationDTO customerRegistrationDTO){
        MyUser myUser = new MyUser();
        myUser.setId(null);
        myUser.setUsername(customerRegistrationDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(customerRegistrationDTO.getPassword());
        myUser.setPassword(hashedPassword);
        myUser.setName(customerRegistrationDTO.getName());
        myUser.setEmail(customerRegistrationDTO.getEmail());
        myUser.setRole("CUSTOMER");
        myUserRepository.save(myUser);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerRegistrationDTO.getPhoneNumber());
        customer.setUser(myUser);
        customerRepository.save(customer);
    }

    // get .. customer info for the log in customer
    public CustomerDTOOUT getMyInfo(Integer customer_id){
      MyUser myUser = myUserRepository.findMyUserById(customer_id);
      if(myUser==null){
          throw new ApiException("Customer id is wrong");
      }
      Customer customer = customerRepository.findCustomerById(customer_id);
        return new CustomerDTOOUT(myUser.getUsername(), myUser.getName(),myUser.getEmail(), customer.getPhoneNumber(),customer.getAccounts());
    }

    // update
    public void updateCustomer(Integer customer_id ,CustomerRegistrationDTO customerRegistrationDTO){
      MyUser myUser = myUserRepository.findMyUserById(customer_id);
      if(myUser==null){
          throw new ApiException("Wrong customer id");
      }
        myUser.setUsername(customerRegistrationDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(customerRegistrationDTO.getPassword());
        myUser.setPassword(hashedPassword);
        myUser.setName(customerRegistrationDTO.getName());
        myUser.setEmail(customerRegistrationDTO.getEmail());
        myUserRepository.save(myUser);

        Customer customer = customerRepository.findCustomerByUser(myUser);
        if (customer == null) {
            throw new ApiException("Customer record not found for the user");
        }
        customer.setUser(myUser);
        customer.setPhoneNumber(customerRegistrationDTO.getPhoneNumber());
        customerRepository.save(customer);
    }

    // delete
    public void deleteCustomer(Integer customer_id){
       MyUser myUser = myUserRepository.findMyUserById(customer_id);
       if(myUser==null){
           throw new ApiException("Wrong Customer Id");
       }
       myUserRepository.delete(myUser);
    }



}
