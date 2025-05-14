package com.alkemy.wallet.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.wallet.models.transaction.TransactionMethodEnum;
import com.alkemy.wallet.models.transaction.Withdrawal;
import com.alkemy.wallet.repository.transaction.WithdrawalRepository;
import com.alkemy.wallet.services.WithdrawalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WithdrawalServiceImpl extends TransactionServiceImpl<Withdrawal> implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository) {
        super(withdrawalRepository); // hereda los métodos comunes
        this.withdrawalRepository = withdrawalRepository;
    }

    //Método para obtener los retiros por sucursal
    @Override
    public List<Withdrawal> getByBranch(String branch) {
        List<Withdrawal> result = withdrawalRepository.findByBranch(branch);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron retiros en la sucursal: " + branch);
        }
        return result;
    }

    //Método para obtener retiros por método
    @Override
    public List<Withdrawal> getByMethod(String method) {
        try {
            TransactionMethodEnum methodEnum = TransactionMethodEnum.valueOf(method.toUpperCase());
            List<Withdrawal> result = withdrawalRepository.findByMethod(methodEnum);
            if (result.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron retiros con el método: " + method);
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de retiro inválido: " + method);
        }
    }

    //Método para obtener retiros por ID de usuario
    // Se asume que el método getByUserId está definido en la interfaz TransactionService
    // y que TransactionService es una interfaz genérica que maneja transacciones
    // en general, no solo retiros.
    @Override
    public List<Withdrawal> getByUserId(int userId) {
        List<Withdrawal> withdrawals = withdrawalRepository.findAll().stream()
                .filter(w -> w.getAccount().getUser().getId() == userId)
                .toList();

        if (withdrawals.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron retiros para el usuario con ID: " + userId);
        }

        return withdrawals;
    }
}
