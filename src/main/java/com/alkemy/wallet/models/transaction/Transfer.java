package com.alkemy.wallet.models.transaction;

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
@PrimaryKeyJoinColumn(name = "id_transaction") // clave heredada de Transaction
public class Transfer extends Transaction {

    @ManyToOne
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id_account")
    private Account destinationAccount;

    @Column(name = "description")
    private String description;

}
