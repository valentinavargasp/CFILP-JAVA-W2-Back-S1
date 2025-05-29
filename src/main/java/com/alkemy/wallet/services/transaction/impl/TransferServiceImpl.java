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

        if (dto.getTransactionDate() == null) {
            dto.setTransactionDate(java.time.LocalDateTime.now());
        }

        // Buscar cuenta origen por ID
        Account cuentaOrigen = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta origen no encontrada"));

        // Buscar cuenta destino: por ID, CBU o alias
        Account cuentaDestino = null;
        if (dto.getDestinationAccountId() != null) {
            cuentaDestino = accountRepository.findById(dto.getDestinationAccountId())
                    .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada por ID"));
        } else if (dto.getRecipientCBU() != null && !dto.getRecipientCBU().isEmpty()) {
            cuentaDestino = accountRepository.findByCbu(dto.getRecipientCBU())
                    .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada por CBU"));
        } else if (dto.getRecipientAlias() != null && !dto.getRecipientAlias().isEmpty()) {
            cuentaDestino = accountRepository.findByAlias(dto.getRecipientAlias())
                    .orElseThrow(() -> new EntityNotFoundException("Cuenta destino no encontrada por alias"));
        } else {
            throw new IllegalArgumentException("Debes especificar destinationAccountId, recipientCBU o recipientAlias");
        }

        double monto = dto.getTransactionAmount();

        // Validar saldo
        if (cuentaOrigen.getBalance() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen");
        }

        // Actualizar saldos
        cuentaOrigen.setBalance(cuentaOrigen.getBalance() - monto);
        cuentaDestino.setBalance(cuentaDestino.getBalance() + monto);

        // Crear transacciones
        Transaction transactionOrigen = new Transaction();
        transactionOrigen.setTransactionAmount(-monto);
        transactionOrigen.setTransactionDate(dto.getTransactionDate());
        transactionOrigen.setAccount(cuentaOrigen);
        transactionOrigen.setTransactionType("TRANSFER_OUT");
        transactionOrigen.setDescription(dto.getDescription());

        Transaction transactionDestino = new Transaction();
        transactionDestino.setTransactionAmount(monto);
        transactionDestino.setTransactionDate(dto.getTransactionDate());
        transactionDestino.setAccount(cuentaDestino);
        transactionDestino.setTransactionType("TRANSFER_IN");
        transactionDestino
                .setDescription("Transferencia recibida de " + cuentaOrigen.getUser().getPerson().getFullName());

        transactionRepository.save(transactionOrigen);
        transactionRepository.save(transactionDestino);

        Account savedOriginAccount  = accountRepository.save(cuentaOrigen);
        Account savedDestinationAccount =  accountRepository.save(cuentaDestino);

        // Crear y guardar transferencia
        Transfer transfer = new Transfer();
        transfer.setAccount(savedOriginAccount);
        transfer.setDestinationAccount(savedDestinationAccount);
        transfer.setTransactionAmount(monto);
        transfer.setTransactionDate(dto.getTransactionDate());
        transfer.setDescription(dto.getDescription());
        transfer.setDestinationAccountOwner(savedDestinationAccount.getUser().getPerson().getFullName());

        Transfer savedTransfer = transferRepository.save(transfer);

        return transferMapper.toDto(savedTransfer);
    }


    @Override
    public List<TransferDTO> getByAccountId(int accountId) {
        List<Transfer> transfers = transferRepository.findByAccountId(accountId);
      
        return transfers.stream()
                .map(transferMapper::toDto)
                .toList();
    }

}
