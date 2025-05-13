package com.alkemy.wallet.models.transaction;

import jakarta.persistence.*;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposit")
    private Integer id;
}
