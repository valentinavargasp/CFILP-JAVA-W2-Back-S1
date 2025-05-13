package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.transaction.TimeDeposit;

public interface TimeDepositRepository extends JpaRepository<TimeDeposit, Integer> {

}
