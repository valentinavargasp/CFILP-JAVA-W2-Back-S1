package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.Card;

import java.util.List;

public interface CardService<T extends Card> extends FinancerProductService<T>{

    public T save(T card);
    public T getById(int id);
    public void deleteById(int id);

    public T getByNumber(String number);
    public T getByExpirationDate(String expirationDate);

    public List<T> getByCardHolderName(String cardHolderName);
    public List<T> getAllByAccountId(int accountId);
    public List<T> getAllByUserId(int userId);


}
