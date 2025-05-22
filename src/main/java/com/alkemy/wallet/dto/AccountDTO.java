package com.alkemy.wallet.dto;
import com.alkemy.wallet.models.account.Currency;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter 
@Setter
public class AccountDTO {

    private String cbu;
    private String alias;
    private double balance;
    private String accountType;
    private Currency currency;

}
