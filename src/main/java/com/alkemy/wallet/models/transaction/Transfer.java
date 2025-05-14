package com.alkemy.wallet.models.transaction;

import jakarta.persistence.*;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer")
    private Integer id;

}
