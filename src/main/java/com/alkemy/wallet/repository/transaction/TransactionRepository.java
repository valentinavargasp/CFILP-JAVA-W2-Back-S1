package com.alkemy.wallet.repository.transaction;

import com.alkemy.wallet.models.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
