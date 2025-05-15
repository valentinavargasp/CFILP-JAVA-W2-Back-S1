package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.DebitCard;

import java.util.List;

public interface DebitCardService extends CardService<DebitCard> {


    public DebitCard save(DebitCard debitCard);
    public DebitCard getById(int id);
    public void deleteById(int id);

    public DebitCard getByNumber(String number);
    public DebitCard getByExpirationDate(String expirationDate);

    public List<DebitCard> getByCardHolderName(String cardHolderName);
    public List<DebitCard> getAllByAccountId(int accountId);
    public List<DebitCard> getAllByUserId(int userId);

}
