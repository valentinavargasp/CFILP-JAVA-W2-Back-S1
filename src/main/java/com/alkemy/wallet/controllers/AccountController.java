package com.alkemy.wallet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alkemy.wallet.services.AccountService;
import com.alkemy.wallet.models.account.Account;



@RestController
@RequestMapping("/api")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        try {
            Account account = accountService.getAccountById(id);
            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account newAccountData) {
        try {
            Account updatedAccount = accountService.editAccount(id, newAccountData);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        try {
            accountService.deleteAccountById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            Account created = accountService.createAccount(account);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}
