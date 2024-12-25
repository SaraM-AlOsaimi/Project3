package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MyUserRepository myUserRepository;

    // CRUD

    // Create a new bank account ,, customer who ganna create this account
    // or maybe the employee create account for the customer !
    public void createAccount(Integer customer_id,Account account){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        if(myUser==null) throw new ApiException("Wrong customer id");
        account.setIsActive(false);
        account.setCustomer(myUser.getCustomer());
        accountRepository.save(account);
        myUserRepository.save(myUser);
    }

    // get .. accounts info for the logging customer
    // List user's accounts
    public List<Account> myAccounts(Integer customer_id){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        if (myUser == null) {
            throw new ApiException("Wrong Customer ID");
        }
        return accountRepository.findAccountsByCustomerId(customer_id);
    }

    // update
    // I did not do an update method cuz it doesn't make since that customer update his accountNumber!
    // and for the balance there is endpoint to update it (Deposit and withdraw money)

    // delete
    public void deleteAccount(Integer customer_id,Integer account_id){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        Account account = accountRepository.findAccountById(account_id);
        if (myUser==null){
            throw new ApiException("Wrong Customer id");
        }
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!customer_id.equals(account.getCustomer().getId())){
            throw new ApiException("you are not authorized to delete this account");
        }
        myUser.getCustomer().getAccounts().remove(account);
        myUserRepository.save(myUser);
        accountRepository.delete(account);
    }

    // Active a bank account
    // the authority for activating bank account could be by : Employee or admin
    public void activeAccount(Integer user_id,Integer account_id){
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        if (myUser==null){
            throw new ApiException("Wrong user id");
        }
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(account.getIsActive()){
            throw new ApiException("Account is already active");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    // View account details ,, one account
    // maybe i will give authority on this endpoint for both customer and employee
    public Account viewAccountDetails(Integer user_id,Integer account_id){
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        if (myUser==null){
            throw new ApiException("Wrong user id");
        }
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!user_id.equals(account.getCustomer().getId()) && !myUser.getRole().equals("EMPLOYEE") && !myUser.getRole().equals("ADMIN")){
            throw new ApiException("You are not authorized to view this account details");
        }
        return account;
    }

    // List user's accounts .. for the admin and employee only!
    public List<Account> getAllAccounts(Integer user_id){
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        if (myUser==null){
            throw new ApiException("Wrong user id");
        }
        return accountRepository.findAll();
    }

    // Deposit and withdraw money
    // Deposit
    // ypu should check before if the account is active or nott
    public void deposit(Integer customer_id ,Integer account_id,Double amount){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        if (myUser==null){
            throw new ApiException("Wrong customer id");
        }
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!customer_id.equals(account.getCustomer().getId())){
            throw new ApiException("You are not authorized to do this process in that account");
        }
        if(!account.getIsActive()){
            throw new ApiException("Account isn't active");
        }
        if(amount<=0) throw new ApiException("please enter a valid amount");
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    // withdraw
    public void withdraw(Integer customer_id ,Integer account_id,Double amount){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        if (myUser==null){
            throw new ApiException("Wrong customer id");
        }
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!customer_id.equals(account.getCustomer().getId())){
            throw new ApiException("You are not authorized to do this process in that account");
        }
        if(!account.getIsActive()){
            throw new ApiException("Account isn't active");
        }
        if(account.getBalance() < amount){
            throw new ApiException("Inefficient account balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    // Transfer funds between accounts
    public void transferFundsBetweenAccounts(Integer customer_id,Integer account1Id,Integer account2Id,Double amount){
        MyUser myUser = myUserRepository.findMyUserById(customer_id);
        if (myUser==null){
            throw new ApiException("Wrong customer id");
        }
        Account account1 = accountRepository.findAccountById(account1Id);
        if(account1==null){
            throw new ApiException("Account not found");
        }
        if(!customer_id.equals(account1.getCustomer().getId())){
            throw new ApiException("You are not authorized to do this process in that account");
        }
        if(!account1.getIsActive()){
            throw new ApiException("Account isn't active");
        }
        Account account2 = accountRepository.findAccountById(account2Id);
        if(account2==null){
            throw new ApiException("Account not found");
        }
        if(!account2.getIsActive()){
            throw new ApiException("Account you are trying to transfer to isn't active");
        }
        if(account1.getBalance() < amount){
            throw new ApiException("Inefficient account balance");
        }
        account1.setBalance(account1.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);
        accountRepository.save(account1);
        accountRepository.save(account2);
    }
    
    // Block bank account
    public void blockAccount(Integer user_id, Integer account_id){
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        if (myUser==null){
            throw new ApiException("Wrong user id");
        }
        Account account = accountRepository.findAccountById(account_id);
        if(account==null){
            throw new ApiException("Account not found");
        }
        if(!account.getIsActive()){
            throw new ApiException("Account is already Inactive");
        }
       account.setIsActive(false);
        accountRepository.save(account);
    }

}
