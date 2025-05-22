package com.alkemy.wallet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter 
@Setter
public class AccountDTO {
    private int id;
    private String cbu;
    private double balance;
    private String alias;
    private String currency;
    private String accountType; // Solo el nombre del tipo de cuenta
}
