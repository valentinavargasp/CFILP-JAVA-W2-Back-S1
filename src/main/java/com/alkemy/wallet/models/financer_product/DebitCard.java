package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;

@Entity
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_debit_card")
    private Integer id;

}
