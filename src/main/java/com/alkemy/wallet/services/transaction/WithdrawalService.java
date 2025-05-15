package com.alkemy.wallet.services.transaction;

import java.util.List;



import com.alkemy.wallet.models.transaction.Withdrawal;


public interface WithdrawalService extends TransactionService<Withdrawal> { // Extiende de TransactionService
    public List<Withdrawal> getByBranch(String branch);

    public List<Withdrawal> getByMethod(String method);
}
