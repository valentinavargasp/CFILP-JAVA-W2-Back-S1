package com.alkemy.wallet.repository.transaction;

import com.alkemy.wallet.models.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByAccountId(int accountId);

    boolean existsByAccountIdAndTransactionDateAfter(int id, LocalDateTime haceUnMes);
}
