package com.alkemy.wallet.services.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.transaction.Deposit;
import com.alkemy.wallet.models.transaction.TransactionMethodEnum;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.transaction.DepositRepository;
import com.alkemy.wallet.services.transaction.DepositService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepositServiceImpl extends TransactionServiceImpl<Deposit> implements DepositService { // Implementa
                                                                                                    // DepositService
    @Autowired
    private AccountRepository accountRepository;

    private final DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        super(depositRepository); // pasa al TransactionServiceImpl
        this.depositRepository = depositRepository;
    }

    // Método para obtener depósitos por método de transacción
    // Se utiliza el método valueOf para convertir el string a un enum
    // TransactionMethodEnum
    // Si el método no es válido, se lanza una IllegalArgumentException
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException
    @Override
    public List<Deposit> getByMethod(String method) {
        try {
            TransactionMethodEnum methodEnum = TransactionMethodEnum.valueOf(method.toUpperCase());
            List<Deposit> deposits = depositRepository.findByMethod(methodEnum);
            if (deposits.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron depósitos con el método: " + method);
            }
            return deposits;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de transacción inválido: " + method);
        }
    }

    // Método para obtener depósitos por entidad de origen
    // Se utiliza el método findBySourceEntityContainingIgnoreCase para buscar
    // depósitos que contengan la entidad de origen
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException

    @Override
    public List<Deposit> getBySourceEntity(String entity) {
        List<Deposit> deposits = depositRepository.findBySourceEntityContainingIgnoreCase(entity);
        if (deposits.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron depósitos con entidad de origen: " + entity);
        }
        return deposits;
    }

    // Método para obtener depósitos por ID de usuario
    // Se utiliza el método findAll para obtener todos los depósitos y luego se
    // filtran por ID de usuario
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException
    @Override
    public List<Deposit> getByUserId(int userId) {
        List<Deposit> deposits = depositRepository.findAll().stream()
                .filter(deposit -> deposit.getAccount().getUser().getId() == userId)
                .toList();

        if (deposits.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron depósitos para el usuario con ID: " + userId);
        }

        return deposits;
    }

    @Override
    public Deposit save(Deposit deposit) {
        Account account = deposit.getAccount();

        // Verificamos que exista la cuenta
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con ID: " + account.getId()));

        // Actualiza el saldo
        double nuevoSaldo = existingAccount.getBalance() + deposit.getTransactionAmount();
        existingAccount.setBalance(nuevoSaldo);
        accountRepository.save(existingAccount);

        // Guarda el depósito
        return super.save(deposit);
    }

}
