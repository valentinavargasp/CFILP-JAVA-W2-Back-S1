package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.Card;
import com.alkemy.wallet.services.financer_product.CardService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public abstract class CardServiceImpl<T extends Card> extends FinancerProductServiceImpl<T> implements CardService<T> {


    protected final JpaRepository<T, Integer> repository;

    protected CardServiceImpl(JpaRepository<T, Integer> repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public T getByNumber(String number) {
        return null;
    }

    @Override
    public T getByExpirationDate(String expirationDate) {
        return null;
    }

}
