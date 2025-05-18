package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.TimeDeposit;

import java.sql.Time;
import java.util.Optional;

public interface TimeDepositRepository extends JpaRepository<TimeDeposit, Integer> {

    Optional<TimeDeposit> findByExpirationDate(String expirationDate);
}
