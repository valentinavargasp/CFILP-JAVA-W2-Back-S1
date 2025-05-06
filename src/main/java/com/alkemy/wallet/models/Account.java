package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int id;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "balance")
    private double balance;

    @Column(name = "alias")
    private String alias;

    @Column(name = "account_type_id")
    private int accountTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    // Constructores
    public Account() {
    }

    public Account(int id, int idUser, String cbu, double balance, String alias, int accountTypeId, Currency currency) {
        this.id = id;
        this.idUser = idUser;
        this.cbu = cbu;
        this.balance = balance;
        this.alias = alias;
        this.accountTypeId = accountTypeId;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", idUser=" + idUser + ", cbu=" + cbu + ", balance=" + balance + ", alias=" + alias
                + ", accountTypeId=" + accountTypeId + ", currency=" + currency + "]";
    }

}
