package com.alkemy.wallet.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account_type")
    private int id;

    @Column(name = "account_type")
    private String accountType;

    // Relaciones
    // Relación con Account
    @OneToMany(mappedBy = "accountType")
    @JsonBackReference
    private List<Account> accounts;

    // contructores
    public AccountType() {

    }

    public AccountType(int id, String accountType) {
        this.id = id;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", accountType=" + accountType +
                " }";
    }

}
