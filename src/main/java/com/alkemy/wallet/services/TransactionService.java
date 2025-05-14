package com.alkemy.wallet.services;

import java.time.LocalDate;
import java.util.List;



import com.alkemy.wallet.models.transaction.Transaction;

/*T> es un tipo genérico que extiende de Transaction.
Esto quiere decir que el servicio puede manejar cualquier
tipo de transacción que extienda de Transaction, como Transfer, Deposit, etc. */


public interface TransactionService<T extends Transaction> {
    public List<T> getAll(); // Lista de transacciones

    public T getById(int id); // Busca una transacción por id

    public List<T> getByDate(LocalDate date); // Lista de transacciones por fecha

    public T save(T transaction); // Guarda una transacción

    public void deleteById(int id); // Elimina una transacción por id

    public List<T> getByUserId(int userId); // Lista de transacciones por usuario
}
