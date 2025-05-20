package com.alkemy.wallet.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransferDTO {
    private int id;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private int accountId;
    private int destinationAccountId;
    private String description;
}
