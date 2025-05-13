package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.TimeDeposit;

public interface TimeDepositRepository extends JpaRepository<TimeDeposit, Integer> {

}
