package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int id;

    @Column(name = "account_type")
    private String accountType;

    //contructores
    public AccountType(){

    }

    public AccountType(int id, String type){
        this.id = id;
        this.accountType = type; 
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", accountType=" + accountType +
                " }";
    }
    
}
