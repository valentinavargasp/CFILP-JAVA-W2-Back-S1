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
@Table(name = "withdrawal")
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_withdrawal")
    private int id;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private TransactionMethodEnum method;

    @Column(name = "branch")
    private String branch;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id_transaction")
    private Transaction transaction;
}
