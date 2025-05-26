package com.alkemy.wallet.services.transaction.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.DepositDTO;
import com.alkemy.wallet.mapper.DepositMapper;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.transaction.Deposit;
import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.models.transaction.TransactionMethodEnum;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.transaction.DepositRepository;
import com.alkemy.wallet.repository.transaction.TransactionRepository;
import com.alkemy.wallet.services.transaction.DepositService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final DepositMapper depositMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    // Método para obtener depósitos por método de transacción
    // Se utiliza el método valueOf para convertir el string a un enum
    // TransactionMethodEnum
    // Si el método no es válido, se lanza una IllegalArgumentException
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException
    @Override
    public List<DepositDTO> getByMethod(String method) {
        try {
            TransactionMethodEnum methodEnum = TransactionMethodEnum.valueOf(method.toUpperCase());
            List<Deposit> deposits = depositRepository.findByMethod(methodEnum);
            if (deposits.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron depósitos con el método: " + method);
            }
            return deposits.stream()
                    .map(depositMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de transacción inválido: " + method);
        }
    }

    // Método para obtener depósitos por entidad de origen
    // Se utiliza el método findBySourceEntityContainingIgnoreCase para buscar
    // depósitos que contengan la entidad de origen
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException

    @Override
    public List<DepositDTO> getBySourceEntity(String entity) {
        List<Deposit> deposits = depositRepository.findBySourceEntityContainingIgnoreCase(entity);
        if (deposits.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron depósitos con entidad de origen: " + entity);
        }
        return deposits.stream()
                .map(depositMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener depósitos por ID de usuario
    // Se utiliza el método findAll para obtener todos los depósitos y luego se
    // filtran por ID de usuario
    // Si no se encuentran depósitos, se lanza una EntityNotFoundException

    @Override
    public List<DepositDTO> getByUserId(int userId) {
        List<DepositDTO> deposits = depositRepository.findAll().stream()
                .filter(deposit -> deposit.getAccount().getUser().getId() == userId)
                .map(depositMapper::toDTO)
                .collect(Collectors.toList());

        if (deposits.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron depósitos para el usuario con ID: " + userId);
        }

        return deposits;
    }

@Override
public DepositDTO save(DepositDTO depositDTO) {
    
    // Asignar la fecha actual si está nula
    if (depositDTO.getTransactionDate() == null) {
        depositDTO.setTransactionDate(java.time.LocalDateTime.now());
    }
       
    // Buscar cuenta por ID
    Account account = accountRepository.findById(depositDTO.getAccountId())
            .orElseThrow(
                    () -> new EntityNotFoundException("Cuenta no encontrada con ID: " + depositDTO.getAccountId()));
    
    //Guardar la transaccion
    Transaction transaction = new Transaction();
    transaction.setTransactionAmount(depositDTO.getTransactionAmount());
    transaction.setTransactionDate(depositDTO.getTransactionDate());
    transaction.setAccount(account); // Asignar la cuenta a la transacción
    transaction.setDescription(depositDTO.getDescription());
    transaction.setTransactionType("DEPOSIT"); // Asignar tipo de transacción
    Transaction savedTransaction = transactionRepository.save(transaction);
               

    // Convertir DTO a entidad
    Deposit deposit = depositMapper.toEntity(depositDTO);
    deposit.setAccount(account); // Asignar la cuenta recuperada
    deposit.setTransaction(savedTransaction); // Asignar la transacción guardada
   

    // Actualizar saldo
    double nuevoSaldo = account.getBalance() + deposit.getTransactionAmount();
    account.setBalance(nuevoSaldo);
    accountRepository.save(account);

    // Guardar depósito y retornar el DTO correspondiente
    Deposit savedDeposit = depositRepository.save(deposit);
    return depositMapper.toDTO(savedDeposit);
}

}
