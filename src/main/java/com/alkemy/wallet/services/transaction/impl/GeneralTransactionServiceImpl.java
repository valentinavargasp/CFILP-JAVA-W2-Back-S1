package com.alkemy.wallet.services.transaction.impl;

import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.repository.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

/**
 * Esta clase concreta implementa TransactionService<Transaction> para que Spring
 * pueda inyectarla correctamente en el TransactionController. 
 *
 * Dado que TransactionServiceImpl es genérica (T extends Transaction), 
 * Spring no puede inferir automáticamente un bean de tipo TransactionService<Transaction>
 * sin esta implementación específica.
 *
 * Esta clase actúa como un "puente" entre la lógica común de transacciones (TransactionServiceImpl)
 * y el controlador genérico (TransactionController), permitiendo exponer operaciones generales
 * de lectura sin depender de las clases hijas como Transfer, Deposit o Withdrawal.
 */

@Service
public class GeneralTransactionServiceImpl extends TransactionServiceImpl<Transaction> {

    public GeneralTransactionServiceImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }
}

