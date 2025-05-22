package com.alkemy.wallet.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.wallet.dto.TransactionDTO;
import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.services.transaction.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//Utilizamos SpringDoc para la documentación de la API y Swagger para la interfaz gráfica
@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transacciones", description = "Operaciones generales de lectura para todas las transacciones")
public class TransactionController {

    private final TransactionService<Transaction> transactionService;

    public TransactionController(TransactionService<Transaction> transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Listar todas las transacciones")
    @ApiResponse(responseCode = "200", description = "Listado completo de transacciones")
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @Operation(summary = "Buscar transacciones por usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
        @ApiResponse(responseCode = "404", description = "No se encontraron transacciones para ese usuario")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDTO>> getByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(transactionService.getByUserId(userId));
    }

    @Operation(summary = "Buscar transacciones por fecha")
    @ApiResponse(responseCode = "200", description = "Transacciones filtradas por fecha")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<TransactionDTO>> getByDate(@PathVariable String date) {
        return ResponseEntity.ok(transactionService.getByDate(LocalDate.parse(date)));
    }
}

