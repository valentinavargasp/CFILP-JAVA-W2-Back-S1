package com.alkemy.wallet.services.transaction;

import java.util.List;



import com.alkemy.wallet.models.transaction.Transfer;


public interface TransferService extends TransactionService<Transfer> { // Extiende de TransactionService
    public List<Transfer> getByDestinationAccountId(int accountId);
}
