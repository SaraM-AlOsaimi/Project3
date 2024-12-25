package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-system/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // create
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Account account){
        accountService.createAccount(user.getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("Account Created Successfully, Thank you for trusting our bank!"));
    }

    // get
    @GetMapping("/get")
    public ResponseEntity<?> myAccounts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(accountService.myAccounts(myUser.getId()));
    }

    // delete
    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal MyUser myUser ,@PathVariable Integer account_id){
        accountService.deleteAccount(myUser.getId(),account_id);
    return ResponseEntity.status(200).body(new ApiResponse("Account deleted Successfully"));
    }

    // Active a bank account
    @PutMapping("/active-account/{account_id}")
    public ResponseEntity<?> activeAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id){
      accountService.activeAccount(myUser.getId(),account_id);
      return ResponseEntity.status(200).body(new ApiResponse("Account Activated Successfully"));
    }

    // View account details
    @GetMapping("/account-detail/{account_id}")
    public ResponseEntity<?> viewAccountDetails(@AuthenticationPrincipal MyUser myUser ,@PathVariable Integer account_id){
      return ResponseEntity.status(200).body(accountService.viewAccountDetails(myUser.getId(),account_id));
    }

    // get All Accounts
    @GetMapping("/all-accounts")
    public ResponseEntity<?> getAllAccounts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(accountService.getAllAccounts(myUser.getId()));
    }

    // deposit
    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id,@PathVariable Double amount){
       accountService.deposit(myUser.getId(),account_id,amount);
       return ResponseEntity.status(200).body(new ApiResponse("deposit done Successfully"));
    }

    // withdraw
    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id,@PathVariable Double amount){
        accountService.withdraw(myUser.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("withdraw done Successfully"));
    }

    // transferFundsBetweenAccounts
    @PutMapping("/transfer-funds/{account1_id}/{account2_id}/{amount}")
    public ResponseEntity<?> transferFundsBetweenAccounts(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account1_id,@PathVariable Integer account2_id,@PathVariable Double amount){
        accountService.transferFundsBetweenAccounts(myUser.getId(),account1_id,account2_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer funds done Successfully"));
    }

    @PutMapping("/block-account/{account_id}")
    public ResponseEntity<?> blockAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer account_id){
        accountService.activeAccount(myUser.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account InActivated Successfully"));
    }
}
