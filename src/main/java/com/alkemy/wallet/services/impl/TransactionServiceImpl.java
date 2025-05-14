package com.alkemy.wallet.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.transaction.Transaction;
import com.alkemy.wallet.services.TransactionService;

public abstract class TransactionServiceImpl<T extends Transaction> implements TransactionService<T> {

    protected final JpaRepository<T, Integer> repository;

    public TransactionServiceImpl(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    // Método para obtener todas las transacciones
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    // Método para obtener una transacción por ID
    // Si no se encuentra la transacción, lanza una excepción
    // IllegalArgumentException
    @Override
    public T getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transacción no encontrada con ID: " + id));
    }

    // Método para guardar una transacción
    // Si la transacción ya existe, lanza una excepción IllegalArgumentException
    @Override
    public T save(T transaction) {
        if (repository.existsById(transaction.getId())) {
            throw new IllegalArgumentException("Transacción ya existe con ID: " + transaction.getId());
        }
        return repository.save(transaction);
    }

    // Método para borrar una transacción
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // Método para obtener transacciones por fecha
    // Filtra las transacciones por fecha y devuelve una lista de transacciones que
    // coinciden con la fecha
    @Override
    public List<T> getByDate(LocalDate date) {
        return repository.findAll().stream()
                .filter(t -> t.getTransactionDate().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    // Método para obtener transacciones por ID de cuenta
    // Filtra las transacciones por ID de cuenta y devuelve una lista de
    // transacciones que coinciden con el ID de cuenta
    @Override
    public List<T> getByUserId(int userId) {
        return repository.findAll().stream()
                .filter(t -> t.getAccount().getUser().getId() == userId)
                .collect(Collectors.toList());
    }
}
