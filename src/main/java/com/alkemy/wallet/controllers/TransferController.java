package com.alkemy.wallet.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.wallet.models.transaction.Transfer;
import com.alkemy.wallet.services.transaction.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Transferencias", description = "Operaciones relacionadas con transferencias")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Operation(summary = "Crear nueva transferencia")
    @ApiResponse(responseCode = "201", description = "Transferencia realizada correctamente")
    @PostMapping
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.status(201).body(transferService.save(transfer));
    }

    @Operation(summary = "Buscar transferencias por cuenta de destino")
    @ApiResponse(responseCode = "200", description = "Transferencias encontradas")
    @GetMapping("/destination/{accountId}")
    public ResponseEntity<List<Transfer>> getByDestinationAccountId(@PathVariable int accountId) {
        return ResponseEntity.ok(transferService.getByDestinationAccountId(accountId));
    }
}
