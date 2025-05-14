package com.alkemy.wallet.models.transaction;

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
@Table(name = "deposit")
@PrimaryKeyJoinColumn(name = "id_transaction") // clave heredada de Transaction
public class Deposit extends Transaction {

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private TransactionMethodEnum method;

    @Column(name = "source_entity")
    private String sourceEntity;

}
