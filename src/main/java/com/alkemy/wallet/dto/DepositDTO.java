package com.alkemy.wallet.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DepositDTO {
    private int id;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private int accountId;
    private String method;
    private String sourceEntity;
    private String description;
}
