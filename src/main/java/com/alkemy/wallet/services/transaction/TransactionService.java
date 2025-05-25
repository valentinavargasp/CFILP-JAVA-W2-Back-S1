package com.alkemy.wallet.services.transaction;

import java.time.LocalDate;
import java.util.List;

import com.alkemy.wallet.dto.TransactionDTO;
import com.alkemy.wallet.models.transaction.Transaction;

public interface TransactionService<T extends Transaction> {
    public List<TransactionDTO> getAll(); // Lista de transacciones

    public TransactionDTO getById(int id); // Busca una transacción por id

    public List<TransactionDTO> getByDate(LocalDate date); // Lista de transacciones por fecha

    public TransactionDTO save(T transaction); // Guarda una transacción

    public void deleteById(int id); // Elimina una transacción por id

    public List<TransactionDTO> getByUserId(int userId); // Lista de transacciones por usuario

    public List<TransactionDTO> getByAccountId(int accountId); // Lista de transacciones por cuenta
}
