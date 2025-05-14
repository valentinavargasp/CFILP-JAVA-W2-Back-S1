package com.alkemy.wallet.models.financer_product;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "time_deposit")
@Entity
public class TimeDeposit extends FinancerProduct {

    @Column (nullable = false, name = "deposit_term")
    private int depositTerm;
    @Column (nullable = false, name = "interest_rate")
    private double interestRate;
    @Column (nullable = false, name = "deposit_amount")
    private double depositAmount;
    @Column (nullable = false, name = "monthly_payment")
    private double totalPayment;
}
