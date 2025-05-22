package com.alkemy.wallet.services.transaction.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.TransferDTO;
import com.alkemy.wallet.mapper.TransferMapper;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.transaction.Transfer;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.transaction.TransferRepository;
import com.alkemy.wallet.services.transaction.TransferService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;


    // Implementación del método para obtener transferencias por ID de cuenta de
    // destino
    // Filtra las transferencias por ID de cuenta de destino y devuelve una lista de
    // transferencias que coinciden con el ID de cuenta de destino
    // Si no se encuentra la transferencia, lanza una excepción
    // IllegalArgumentException
    @Override
    public List<TransferDTO> getByDestinationAccountId(int accountId) {
        List<Transfer> transfers = transferRepository.findByDestinationAccount_Id(accountId);
        if (transfers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontraron transferencias con ID de cuenta de destino: " + accountId);
        }
        return transfers.stream()
                .map(transferMapper::toDto)
                .toList();
    }

    // Implementaciones propias de TransferService
    // Método para obtener transferencias por ID de usuario
    @Override
    public List<TransferDTO> getByUserId(int userId) {
        List<TransferDTO> transfers = transferRepository.findAll().stream()
                .filter(t -> t.getAccount().getUser().getId() == userId)
                .map(transferMapper::toDto)
                .toList();
        if (transfers.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron transferencias con ID de usuario: " + userId);
        }
        return transfers;
    }

    /*
     * Al guardar una transferencia:
     * 1. Se busca la cuenta origen y la cuenta destino.
     * 2. Se verifica que haya saldo suficiente en la cuenta origen.
     * 3. Se resta el monto de la cuenta origen y se suma en la cuenta destino.
     * 4. Se guardan las cuentas actualizadas.
     * 5. Luego, se guarda la transacción como tal (super.save).
     */
@Override
public TransferDTO save(TransferDTO dto) {
    Transfer transfer = transferMapper.toEntity(dto);

    Account origen = transfer.getAccount();
    Account destino = transfer.getDestinationAccount();
    double monto = transfer.getTransactionAmount();

    Account cuentaOrigen = accountRepository.findById(origen.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cuenta origen no encontrada"));
    Account cuentaDestino = accountRepository.findById(destino.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada"));

    if (cuentaOrigen.getBalance() < monto) {
        throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen");
    }

    cuentaOrigen.setBalance(cuentaOrigen.getBalance() - monto);
    cuentaDestino.setBalance(cuentaDestino.getBalance() + monto);

    accountRepository.save(cuentaOrigen);
    accountRepository.save(cuentaDestino);

    Transfer savedTransfer = transferRepository.save(transfer);

    return transferMapper.toDto(savedTransfer);
}


}
