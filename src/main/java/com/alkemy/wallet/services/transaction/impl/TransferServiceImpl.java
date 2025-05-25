package com.alkemy.wallet.services.transaction.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.TransferDTO;
import com.alkemy.wallet.mapper.TransferMapper;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.models.transaction.Transfer;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.transaction.TransactionRepository;
import com.alkemy.wallet.repository.transaction.TransferRepository;
import com.alkemy.wallet.services.transaction.TransferService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransactionRepository transactionRepository;
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

    if(dto.getTransactionDate() == null) {
        dto.setTransactionDate(java.time.LocalDateTime.now());
    }

    Transfer transfer = transferMapper.toEntity(dto);
    // 1. Buscar cuentas origen y destino
    Account origen = transfer.getAccount();
    Account destino = transfer.getDestinationAccount();
    double monto = transfer.getTransactionAmount();

    Account cuentaOrigen = accountRepository.findById(origen.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cuenta origen no encontrada"));
    Account cuentaDestino = accountRepository.findById(destino.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada"));

    // 2. Verificar saldo suficiente en la cuenta origen
    if (cuentaOrigen.getBalance() < monto) {
        throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen");
    }

    // 3. Actualizar saldos de las cuentas
    cuentaOrigen.setBalance(cuentaOrigen.getBalance() - monto);
    cuentaDestino.setBalance(cuentaDestino.getBalance() + monto);

    //4. Se crea la trasnacción para ambas cuenttas 
        // Transacción para la cuenta origen (monto negativo)
    Transaction transactionOrigen = new Transaction();
    transactionOrigen.setTransactionAmount(-monto); 
    transactionOrigen.setTransactionDate(dto.getTransactionDate());
    transactionOrigen.setAccount(cuentaOrigen);
    transactionOrigen.setTransactionType("TRANSFER_OUT");   
    transactionOrigen.setDescription(dto.getDescription());
        // Transacción para la cuenta destino (monto positivo)
    Transaction transactionDestino = new Transaction();
    transactionDestino.setTransactionAmount(monto); 
    transactionDestino.setTransactionDate(dto.getTransactionDate());
    transactionDestino.setAccount(cuentaDestino);
    transactionDestino.setTransactionType("TRANSFER_IN");
    transactionDestino.setDescription("Transferencia recibida de " + cuentaOrigen.getUser().getPerson().getFullName());

    // Guardar las transacciones
    transactionRepository.save(transactionOrigen);
    transactionRepository.save(transactionDestino);


    // 5. Guardar las cuentas actualizadas
    accountRepository.save(cuentaOrigen);
    accountRepository.save(cuentaDestino);

    // 6. Guardar la transferencia
    Transfer savedTransfer = transferRepository.save(transfer);

    return transferMapper.toDto(savedTransfer);
}


}
