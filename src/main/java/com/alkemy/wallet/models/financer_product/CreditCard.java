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

    @Column(name = "available_credit")
    private Double availableCredit; //credito disponible
    @Column(name = "credit_limit")
    private Double creditLimit;  // limite de credito maximo
    @Column(name = "interest_rate")
    private Double interestRate;  //interes mensual
    @Column(name = "monthly_payment")
    private Double monthlyPayment; // pago mensual (podria ser segun las cuotas que se le asignen)
    @Column(name = "total_payment")
    private Double totalPayment;  // suma de los pagos mensuales

}
