package com.alkemy.wallet.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.transaction.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

}
