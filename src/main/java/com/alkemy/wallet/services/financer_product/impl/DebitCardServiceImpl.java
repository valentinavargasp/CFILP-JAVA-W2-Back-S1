package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.exceptions.InsufficientFundsException;
import com.alkemy.wallet.models.financer_product.DebitCard;
import com.alkemy.wallet.repository.financer_product.DebitCardRepository;
import com.alkemy.wallet.services.financer_product.DebitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {
    
    private final DebitCardRepository debitCardRepository;

    @Override
    public DebitCard getByNumber(String number) {
        return debitCardRepository.findByCardNumber(number);
    }

    @Override
    public List<DebitCard> getByExpirationDate(String expirationDate) {
        if(expirationDate == null || expirationDate.trim().isEmpty()){
            throw new IllegalArgumentException("La fecha no es valida");
        }
        try{
            return debitCardRepository.findAll().stream()
                    .filter(card -> card.getExpirationDate().toString().equals(expirationDate))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public List<DebitCard> getByCardHolderName(String cardHolderName) {
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {    
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío");
        }
        
        try {
            List<DebitCard> cards = debitCardRepository.findAll().stream()
                    .filter(card -> card.getCardHolderName().equals(cardHolderName))
                    .toList();
            if (cards.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron tarjetas para el usuario: " + cardHolderName);
            }
            return cards;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar tarjetas por nombre del usuario", e);
        }
        
    }

    @Override
    public DebitCard getById(int id) {
        try {
            return debitCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta de débito no encontrada con ID: " + id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        debitCardRepository.deleteById(id);
    }

    @Override
    public DebitCard save(DebitCard debitCard) {
        try{
            return debitCardRepository.save(debitCard);
        }catch (Exception e){
            throw new RuntimeException("Error al guardar el producto", e);
        }
    }

    @Override
    public List<DebitCard> getAllByAccountId(int accountId) {
        try{
            return debitCardRepository.findAll().stream()
                    .filter(p -> p.getAccount().getId() == accountId)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("Error al obtener las tarjetas de débito", e);
        }
    }

    @Override
    public List<DebitCard> getAllByUserId(int userId) {
        try{
            return debitCardRepository.findAll().stream()
                    .filter(p -> p.getAccount().getUser().getId() == userId)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //TODO:  debito disponible
    @Override
    public double getAvailableBalance(int debitCardId) {
        return 0;

    }


    @Override
    public void payWithDebitCard(int debitCardId, BigDecimal amount) throws InsufficientFundsException {

    }
}