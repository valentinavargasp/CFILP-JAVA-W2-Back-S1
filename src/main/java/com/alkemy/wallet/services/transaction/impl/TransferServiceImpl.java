package com.alkemy.wallet.services.transaction.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.wallet.models.transaction.Transfer;
import com.alkemy.wallet.repository.transaction.TransferRepository;
import com.alkemy.wallet.services.transaction.TransferService;

@Service
public class TransferServiceImpl extends TransactionServiceImpl<Transfer> implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        super(transferRepository); // para el constructor del genérico
        this.transferRepository = transferRepository;
    }

    // Implementación del método para obtener transferencias por ID de cuenta de
    // destino
    // Filtra las transferencias por ID de cuenta de destino y devuelve una lista de
    // transferencias que coinciden con el ID de cuenta de destino
    // Si no se encuentra la transferencia, lanza una excepción
    // IllegalArgumentException
    @Override
    public List<Transfer> getByDestinationAccountId(int accountId) {
        List<Transfer> transfers = transferRepository.findByDestinationAccount_Id(accountId);
        if (transfers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontraron transferencias con ID de cuenta de destino: " + accountId);
        }
        return transfers;
    }

    // Implementaciones propias de TransferService
    // Método para obtener transferencias por ID de usuario
    @Override
    public List<Transfer> getByUserId(int userId) {
        List<Transfer> transfers = transferRepository.findAll().stream()
                .filter(t -> t.getAccount().getUser().getId() == userId)
                .toList();
        if (transfers.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron transferencias con ID de usuario: " + userId);
        }
        return transfers;
    }


    //TODO: TRANSFERIR TIENE QUE ACtUALIZAR EL SALDO EN LAS CUENTAS DE LA TRANSACCION
}
