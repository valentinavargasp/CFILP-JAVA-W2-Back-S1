package com.alkemy.wallet.models.transaction;

import jakarta.persistence.*;

@Entity
public class Withdrawal extends Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_withdrawal")
    private Integer id;

}
