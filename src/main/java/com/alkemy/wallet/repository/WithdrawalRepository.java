package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {
    
}
