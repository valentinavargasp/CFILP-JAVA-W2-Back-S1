package com.alkemy.wallet.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.wallet.models.transaction.Deposit;
import com.alkemy.wallet.services.transaction.DepositService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/deposits")
@Tag(name = "Depósitos", description = "Operaciones relacionadas con depósitos")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @Operation(summary = "Realizar un depósito")
    @ApiResponse(responseCode = "201", description = "Depósito realizado exitosamente")
    @PostMapping
    public ResponseEntity<Deposit> createDeposit(@RequestBody Deposit deposit) {
        return ResponseEntity.status(201).body(depositService.save(deposit));
    }

    @Operation(summary = "Buscar depósitos por entidad de origen")
    @ApiResponse(responseCode = "200", description = "Depósitos encontrados")
    @GetMapping("/entity/{entity}")
    public ResponseEntity<List<Deposit>> getBySourceEntity(@PathVariable String entity) {
        return ResponseEntity.ok(depositService.getBySourceEntity(entity));
    }

    @Operation(summary = "Buscar depósitos por método")
    @ApiResponse(responseCode = "200", description = "Depósitos encontrados")
    @GetMapping("/method/{method}")
    public ResponseEntity<List<Deposit>> getByMethod(@PathVariable String method) {
        return ResponseEntity.ok(depositService.getByMethod(method));
    }
}