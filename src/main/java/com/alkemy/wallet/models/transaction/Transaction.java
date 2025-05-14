package com.alkemy.wallet.models.transaction;

import java.time.LocalDateTime;

import com.alkemy.wallet.models.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transaction")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private int id;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Column(name = "transaction_amount")
    private double transactionAmount;

    // Relacionar con la cuenta
    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private Account account;

    // Constructores
    public Transaction() {
    }

    public Transaction(double transactionAmount, Account account) {
        this.transactionAmount = transactionAmount;
        this.account = account;
    }
    
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", account=" + account + ", transactionDate=" + transactionDate
                + ", transactionAmount=" + transactionAmount + "]";
    }

}
