package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

}
