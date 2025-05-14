package com.alkemy.wallet.services;

import java.util.List;



import com.alkemy.wallet.models.transaction.Deposit;


public interface DepositService extends TransactionService<Deposit> { // Extiende de TransactionService
    public List<Deposit> getByMethod(String method); // Ejemplo de método específico

    public List<Deposit> getBySourceEntity(String entity); // Lista de depósitos por entidad de origen
}
