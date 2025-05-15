package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.FinancerProduct;
import com.alkemy.wallet.repository.financer_product.FinancerProductRepository;
import com.alkemy.wallet.services.financer_product.FinancerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class FinancerProductServiceImpl<T extends FinancerProduct> implements FinancerProductService<T> {

    @Autowired
    private FinancerProductRepository financerProductRepository;


    @Override
    public T save(T financerProduct) {
        return null;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<T> getAllByAccountId(int accountId) {
        return List.of();
    }
}
