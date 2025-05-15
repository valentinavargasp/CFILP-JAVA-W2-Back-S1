package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.CreditCard;

import java.util.List;

public interface CreditCardService extends CardService<CreditCard>{

    public CreditCard save(CreditCard creditCard);
    public CreditCard getById(int id);
    public void deleteById(int id);

    public CreditCard getByNumber(String number);
    public CreditCard getByExpirationDate(String expirationDate);

    public List<CreditCard> getByCardHolderName(String cardHolderName);
    public List<CreditCard> getAllByAccountId(int accountId);
    public List<CreditCard> getAllByUserId(int userId);

}
