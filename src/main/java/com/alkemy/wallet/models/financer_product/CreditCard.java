package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "credit_card")
@Entity
public class CreditCard extends Card{

    private Double availableCredit;
    private Double creditLimit;
    private Double interestRate;
    private Double monthlyPayment;
    private Double totalPayment;
    private Double totalInterest;


}
