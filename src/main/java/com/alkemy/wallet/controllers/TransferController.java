package com.alkemy.wallet.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.wallet.dto.TransferDTO;
import com.alkemy.wallet.services.transaction.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO transferDTO) {
        return ResponseEntity.status(201).body(transferService.save(transferDTO));
    }

    @Operation(summary = "Buscar transferencias por cuenta de destino")
    @ApiResponse(responseCode = "200", description = "Transferencias encontradas")
    @GetMapping("/destination/{accountId}")
    public ResponseEntity<List<TransferDTO>> getByDestinationAccountId(@PathVariable int accountId) {
        return ResponseEntity.ok(transferService.getByDestinationAccountId(accountId));
    }

    @Operation(summary = "Buscar transferencias por cuenta")
    @ApiResponse(responseCode = "200", description = "Transferencias encontradas")
    @ApiResponse(responseCode = "204", description = "No se encontraron transferencias")
    @GetMapping("/account/{accountId}") 
    public ResponseEntity<List<TransferDTO>> getByAccountId(@PathVariable int accountId) {
        List<TransferDTO> transfers = transferService.getByAccountId(accountId);
        if (transfers.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(transfers); // 200 OK
    }
}
