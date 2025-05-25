package com.alkemy.wallet.services.transaction.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.WithdrawalDTO;
import com.alkemy.wallet.mapper.WithdrawalMapper;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.models.transaction.TransactionMethodEnum;
import com.alkemy.wallet.models.transaction.Withdrawal;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.transaction.TransactionRepository;
import com.alkemy.wallet.repository.transaction.WithdrawalRepository;
import com.alkemy.wallet.services.transaction.WithdrawalService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {

    private final AccountRepository accountRepository;
    private final WithdrawalRepository withdrawalRepository;
    private final TransactionRepository transactionRepository;
    private final WithdrawalMapper withdrawalMapper;


    // Método para obtener los retiros por sucursal
    @Override
    public List<WithdrawalDTO> getByBranch(String branch) {
        List<Withdrawal> result = withdrawalRepository.findByBranch(branch);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron retiros en la sucursal: " + branch);
        }
        return withdrawalMapper.toDtoList(result);
    }

    // Método para obtener retiros por método
    @Override
    public List<WithdrawalDTO> getByMethod(String method) {
        try {
            TransactionMethodEnum methodEnum = TransactionMethodEnum.valueOf(method.toUpperCase());
            List<Withdrawal> result = withdrawalRepository.findByMethod(methodEnum);
            if (result.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron retiros con el método: " + method);
            }
            return withdrawalMapper.toDtoList(result);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de retiro inválido: " + method);
        }
    }

    // Método para obtener retiros por ID de usuario
    // Se asume que el método getByUserId está definido en la interfaz
    // TransactionService
    // y que TransactionService es una interfaz genérica que maneja transacciones
    // en general, no solo retiros.
    @Override
    public List<WithdrawalDTO> getByUserId(int userId) {
        List<Withdrawal> withdrawals = withdrawalRepository.findAll().stream()
                .filter(w -> w.getAccount().getUser().getId() == userId)
                .toList();

        if (withdrawals.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron retiros para el usuario con ID: " + userId);
        }

        return withdrawalMapper.toDtoList(withdrawals);
    }

    /**
     * Al guardar un retiro:
     * 1. Buscamos la cuenta origen.
     * 2. Verificamos que tenga saldo suficiente.
     * 3. Restamos el monto y actualizamos la cuenta.
     * 4. Finalmente, guardamos la transacción.
     */
@Override
public WithdrawalDTO save(WithdrawalDTO withdrawalDTO) {
    // 1. Buscar la cuenta por ID desde el DTO
    Account account = accountRepository.findById(withdrawalDTO.getAccountId())
        .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con ID: " + withdrawalDTO.getAccountId()));

    // 2. Verificar saldo suficiente
    double monto = withdrawalDTO.getTransactionAmount();
    if (account.getBalance() < monto) {
        throw new IllegalArgumentException("Saldo insuficiente para retirar");
    }

    // 3. Crear y guardar la transacción
    Transaction transaction = new Transaction();
    transaction.setTransactionAmount(monto);
    transaction.setTransactionDate(withdrawalDTO.getTransactionDate() != null ? withdrawalDTO.getTransactionDate() : java.time.LocalDateTime.now());
    transaction.setAccount(account);
    Transaction savedTransaction = transactionRepository.save(transaction);

    // 4. Mapear el DTO a entidad y asociar cuenta y transacción
    Withdrawal withdrawal = withdrawalMapper.toEntity(withdrawalDTO);
    withdrawal.setAccount(account);
    withdrawal.setTransaction(savedTransaction);

    // 5. Actualizar saldo y guardar
    account.setBalance(account.getBalance() - monto);
    accountRepository.save(account);

    return withdrawalMapper.toDto(withdrawalRepository.save(withdrawal));
}

}