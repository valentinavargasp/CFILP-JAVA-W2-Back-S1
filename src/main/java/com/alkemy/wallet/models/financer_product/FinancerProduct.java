package com.alkemy.wallet.models.financer_product;

import jakarta.persistence.*;

@Entity
public class FinancerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financer_product")
    private Integer id;
}
