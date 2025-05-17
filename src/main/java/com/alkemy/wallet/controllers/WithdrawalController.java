package com.alkemy.wallet.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.wallet.models.transaction.Withdrawal;
import com.alkemy.wallet.services.transaction.WithdrawalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/withdrawals")
@Tag(name = "Retiros", description = "Operaciones relacionadas con retiros de dinero")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @Operation(summary = "Realizar un retiro")
    @ApiResponse(responseCode = "201", description = "Retiro realizado exitosamente")
    @PostMapping
    public ResponseEntity<Withdrawal> createWithdrawal(@RequestBody Withdrawal withdrawal) {
        return ResponseEntity.status(201).body(withdrawalService.save(withdrawal));
    }

    @Operation(summary = "Buscar retiros por sucursal")
    @ApiResponse(responseCode = "200", description = "Retiros encontrados")
    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<Withdrawal>> getByBranch(@PathVariable String branch) {
        return ResponseEntity.ok(withdrawalService.getByBranch(branch));
    }

    @Operation(summary = "Buscar retiros por método")
    @ApiResponse(responseCode = "200", description = "Retiros encontrados")
    @GetMapping("/method/{method}")
    public ResponseEntity<List<Withdrawal>> getByMethod(@PathVariable String method) {
        return ResponseEntity.ok(withdrawalService.getByMethod(method));
    }
}
