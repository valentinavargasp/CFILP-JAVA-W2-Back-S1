package com.alkemy.wallet.repository.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.wallet.models.transaction.TransactionMethodEnum;
import com.alkemy.wallet.models.transaction.Withdrawal;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {
    public List<Withdrawal> findByBranch(String branch);

    public List<Withdrawal> findByMethod(TransactionMethodEnum method);
}
