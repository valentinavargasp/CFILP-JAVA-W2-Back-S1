package com.alkemy.wallet.repository.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.wallet.models.transaction.Deposit;
import com.alkemy.wallet.models.transaction.TransactionMethodEnum;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {
    List<Deposit> findByMethod(TransactionMethodEnum method);

    List<Deposit> findBySourceEntityContainingIgnoreCase(String sourceEntity);
}
