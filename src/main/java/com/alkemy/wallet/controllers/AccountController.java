package com.alkemy.wallet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alkemy.wallet.services.account.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.financer_product.FinancerProduct;
import com.alkemy.wallet.models.transaction.Transaction;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Crear nueva cuenta")
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account created = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Listar todas las cuentas")
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @Operation(summary = "Obtener cuenta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Listar cuentas por usuario")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(accountService.getAllAccountByUserId(userId));
    }

    @Operation(summary = "Actualizar cuenta por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account newAccountData) {
        Account updatedAccount = accountService.editAccount(id, newAccountData);
        return ResponseEntity.ok(updatedAccount);
    }

    @Operation(summary = "Eliminar cuenta por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener transacciones de una cuenta")
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable int accountId) {
        return ResponseEntity.ok(accountService.getAccountTransactions(accountId));
    }

    @Operation(summary = "Obtener productos financieros de una cuenta")
    @GetMapping("/{accountId}/financer-products")
    public ResponseEntity<List<FinancerProduct>> getFinancerProducts(@PathVariable int accountId) {
        return ResponseEntity.ok(accountService.getAccountFinancerProducts(accountId));
    }

}
