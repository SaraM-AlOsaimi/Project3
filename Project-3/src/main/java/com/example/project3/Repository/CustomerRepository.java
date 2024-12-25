package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);

    Customer findCustomerByUser(MyUser user);

    @Query("select c from Customer c where c.")
    Customer findCustomerByAccountId(Integer id);

}
