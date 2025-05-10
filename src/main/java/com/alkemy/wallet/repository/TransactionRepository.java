package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
