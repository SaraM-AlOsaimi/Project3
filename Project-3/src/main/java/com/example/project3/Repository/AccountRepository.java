package com.example.project3.Repository;

import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountById(Integer id);

    List<Account> findAccountsById(Integer id);
    
    @Query("select a from Account a where a.customer.id=?1")
    List<Account> findAccountsByCustomerId(Integer customer_id);


}
