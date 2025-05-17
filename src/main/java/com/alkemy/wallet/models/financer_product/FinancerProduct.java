package com.alkemy.wallet.models.financer_product;

import com.alkemy.wallet.models.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "financer_product")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FinancerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financer_product")
    private Integer id;

    @Column(nullable = false, name = "acquisition_date")
    private LocalDate acquisitionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}
