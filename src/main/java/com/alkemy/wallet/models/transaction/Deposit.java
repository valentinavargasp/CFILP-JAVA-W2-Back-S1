package com.alkemy.wallet.models.transaction;

import jakarta.persistence.*;

@Entity
public class Deposit extends Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposit")
    private Integer id;
}
