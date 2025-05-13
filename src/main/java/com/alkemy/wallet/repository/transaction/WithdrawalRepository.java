package com.alkemy.wallet.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.transaction.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {
    
}
