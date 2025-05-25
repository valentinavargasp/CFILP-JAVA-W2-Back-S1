package com.alkemy.wallet.services.transaction.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.TransactionDTO;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.repository.transaction.TransactionRepository;
import com.alkemy.wallet.services.transaction.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService<Transaction> {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    // Método para obtener todas las transacciones
    @Override
    public List<TransactionDTO> getAll() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Método para obtener una transacción por ID
    // Si no se encuentra la transacción, lanza una excepción
    // IllegalArgumentException
    @Override
    public TransactionDTO getById(int id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Transacción no encontrada con ID: " + id));
    }

    // Método para guardar una transacción
    // Si la transacción ya existe, lanza una excepción IllegalArgumentException
    public TransactionDTO save(TransactionDTO transactionDTO) {
        if (transactionRepository.existsById(transactionDTO.getId())) {
            throw new IllegalArgumentException("Transacción ya existe con ID: " + transactionDTO.getId());
        }
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    // Método para borrar una transacción
    @Override
    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }

    // Método para obtener transacciones por fecha
    // Filtra las transacciones por fecha y devuelve una lista de transacciones que
    // coinciden con la fecha
    @Override
    public List<TransactionDTO> getByDate(LocalDate date) {
        return transactionRepository.findAll().stream()
                .filter(t -> t.getTransactionDate().toLocalDate().equals(date))
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Método para obtener transacciones por ID de cuenta
    // Filtra las transacciones por ID de cuenta y devuelve una lista de
    // transacciones que coinciden con el ID de cuenta
    @Override
    public List<TransactionDTO> getByUserId(int userId) {
        return transactionRepository.findAll().stream()
                .filter(t -> t.getAccount().getUser().getId() == userId)
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO save(Transaction transaction) {
        if (transactionRepository.existsById(transaction.getId())) {
            throw new IllegalArgumentException("Transacción ya existe con ID: " + transaction.getId());
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

}
