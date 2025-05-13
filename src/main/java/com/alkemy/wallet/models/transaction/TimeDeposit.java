package com.alkemy.wallet.models.transaction;


import jakarta.persistence.*;

@Entity
public class TimeDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time_deposit")
    private Integer id;
}
