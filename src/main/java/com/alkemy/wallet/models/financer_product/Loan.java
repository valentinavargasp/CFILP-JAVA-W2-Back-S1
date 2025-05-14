package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "loan")
public class Loan extends FinancerProduct{

    @Column(nullable = false, name = "loan_term")
    private int loanTerm;
    @Column(nullable = false, name = "interest_rate")
    private double interestRate;
    @Column(nullable = false, name = "loan_amount")
    private double loanAmount;
    @Column(nullable = false, name = "monthly_payment")
    private double monthlyPayment;
    @Column(nullable = false, name = "total_payment")
    private double totalPayment;
    @Column(nullable = false, name = "remaining_balance")
    private double remainingBalance;
    @Column(nullable = false, name = "total_interest")
    private double totalInterest;
}
