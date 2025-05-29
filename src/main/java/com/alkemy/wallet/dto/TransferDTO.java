package com.alkemy.wallet.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransferDTO {
    private int id;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private int accountId;
    private Integer destinationAccountId;
    private String description;
    private String recipientCBU;
    private String recipientAlias;
    private String destinationAccountOwner;

}
