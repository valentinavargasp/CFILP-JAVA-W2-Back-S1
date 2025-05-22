package com.alkemy.wallet.services.transaction;

import java.util.List;

import com.alkemy.wallet.dto.DepositDTO;


public interface DepositService { // Extiende de TransactionService
    public List<DepositDTO> getByMethod(String method); // Ejemplo de método específico

    public List<DepositDTO> getBySourceEntity(String entity); // Lista de depósitos por entidad de origen

    List<DepositDTO> getByUserId(int userId);   

    DepositDTO save(DepositDTO depositDTO);     
}
