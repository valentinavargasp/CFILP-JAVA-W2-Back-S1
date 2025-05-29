package com.alkemy.wallet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountDTO {
    private int id;
    private int userId; // ID del usuario propietario de la cuenta
    private String accountName; // Número de cuenta
    private String cbu;
    private double balance;
    private String alias;
    private String currency;
    private String accountType; // Solo el nombre del tipo de cuenta
    private String status; // <--  estado (ej: "active", "inactive")
    private String creationDate; // <-- fecha de creación (formato ISO o yyyy-MM-dd)
    private String userName;      // <-- nombre de usuario
}

// Tipos de cuenta
// 1 caja de ahorro
// 2 cuenta corriente
// 3 cuenta de inversion
