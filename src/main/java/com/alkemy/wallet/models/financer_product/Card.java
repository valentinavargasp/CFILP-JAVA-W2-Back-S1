package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "card")
public abstract class Card extends FinancerProduct {

    @Column(nullable = false, name = "card_number")
    private String cardNumber;

    @Column(nullable = false, name = "expiration_date")
    private LocalDate expirationDate;

    @Column(nullable = false, name = "security_code")
    private Integer securityCode;

    @Column(nullable = false, name = "holder_name")
    private String nameHolder;

    @PrePersist
    @PreUpdate
    private void setNameHolderFromPerson() {
        if (getAccount() != null
                && getAccount().getUser() != null
                && getAccount().getUser().getPerson() != null) {
            this.nameHolder = getAccount().getUser().getPerson().getFullName();
        }
    }
}
