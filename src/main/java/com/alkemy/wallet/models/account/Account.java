package com.alkemy.wallet.models.account;

import java.time.LocalDateTime;

import com.alkemy.wallet.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private int id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "balance")
    private double balance;

    @Column(name = "alias")
    private String alias;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    // Relaciones
    @ManyToOne // Un usuario puede tener muchas cuentas
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @JsonBackReference // Evita la recursividad infinita al serializar
    private User user;

    @ManyToOne // Un tipo de cuenta puede tener muchas cuentas
    @JoinColumn(name = "account_type_id", referencedColumnName = "id_account_type")
    private AccountType accountType;

    // Constructores
    public Account() {
    }

    public Account(User user, String cbu, double balance, String alias, AccountType accountType, Currency currency,
            String status, LocalDateTime creationDate) {
        this.user = user;
        this.cbu = cbu;
        this.balance = balance;
        this.alias = alias;
        this.accountType = accountType;
        this.currency = currency;
        this.status = status;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", cbu=" + cbu + ", balance=" + balance + ", alias=" + alias
                + ", accountType=" + accountType + ", currency=" + currency + ", status=" + status
                + ", creationDate=" + creationDate + "]";
    }

}
