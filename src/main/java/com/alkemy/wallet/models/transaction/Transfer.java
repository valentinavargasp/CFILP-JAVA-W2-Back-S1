package com.alkemy.wallet.models.transaction;

import java.time.LocalDateTime;

import com.alkemy.wallet.models.account.Account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer")
    private int id;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id_account")
    private Account destinationAccount;

    @Column(name = "description")
    private String description;

}
