package com.alkemy.wallet.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDTO {

    private int id;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private int accountId; 
    private String description;
    private String transactionType; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
}
