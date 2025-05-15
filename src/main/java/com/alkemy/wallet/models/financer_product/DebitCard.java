package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "debit_card")
public class DebitCard extends Card{

    @Column(name = "available_debit")
    private Double availableDebit;

    @PrePersist
    @PreUpdate
    private void setAvailableDebit() {
        if (getAccount() != null
                && getAccount().getBalance() >= 0
                ) {
            this.availableDebit = getAccount().getBalance();
        }
    }
}
