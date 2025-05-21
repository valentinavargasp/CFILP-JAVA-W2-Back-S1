package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.CreditCard;
import com.alkemy.wallet.models.financer_product.DebitCard;
import com.alkemy.wallet.repository.financer_product.CreditCardRepository;
import com.alkemy.wallet.services.financer_product.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCard;
        }


    @Override
    public CreditCard getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public CreditCard getByNumber(String number) {
        return null;
    }

    @Override
    public List<DebitCard> getByExpirationDate(String expirationDate) {
        return null;
    }

    @Override
    public List<CreditCard> getByCardHolderName(String cardHolderName) {
        return List.of();
    }

    @Override
    public List<CreditCard> getAllByAccountId(int accountId) {
        return List.of();
    }

    @Override
    public List<CreditCard> getAllByUserId(int userId) {
        return List.of();
    }
}
