package com.alkemy.wallet.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transaction_date = LocalDateTime.now();

    @Column(name = "transaction_amount")
    private double transactionAmount;

    // Constructores
    public Transaction() {
    }

    public Transaction(int id, int accountId, LocalDateTime transaction_date, double transactionAmount) {
        this.id = id;
        this.accountId = accountId;
        this.transaction_date = transaction_date;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", accountId=" + accountId + ", transaction_date=" + transaction_date
                + ", transactionAmount=" + transactionAmount + "]";
    }

}
