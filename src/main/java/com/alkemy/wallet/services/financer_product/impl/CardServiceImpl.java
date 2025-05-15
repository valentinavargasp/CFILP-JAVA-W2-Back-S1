package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.Card;
import com.alkemy.wallet.services.financer_product.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class CardServiceImpl<T extends Card> extends FinancerProductServiceImpl<T> implements CardService<T> {


    protected final JpaRepository<T, Integer> repository;

    protected CardServiceImpl(JpaRepository<T, Integer> repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public T getByNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser nulo o vacío");
        }

        return repository.findAll().stream()
            .filter(card -> card.getCardNumber().equals(number))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(
                "No se encontró una tarjeta con el número: " + number));
    }

    @Override
    public T getByExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        
        try {
            LocalDate parsedDate = LocalDate.parse(expirationDate);
            return repository.findAll().stream()
                .filter(card -> card.getExpirationDate().equals(parsedDate))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                    "No se encontró la Tarjeta con la fecha de expiración: " + expirationDate));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido: " + expirationDate, e);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la tarjeta por fecha de expiración", e);
        }
    }

    @Override
    public List<T> getByCardHolderName(String cardHolderName) {
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del titular no puede ser nulo o vacío");
        }
        try {
            List<T> cards = repository.findAll().stream()
                    .filter(card -> card.getCardHolderName().equals(cardHolderName))
                    .toList();
            if (cards.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron tarjetas para el titular: " + cardHolderName);
            }
            return cards;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar tarjetas por nombre del titular", e);
        }
    }
}