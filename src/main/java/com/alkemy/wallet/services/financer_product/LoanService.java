package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.Loan;

import java.util.List;

public interface LoanService extends FinancerProductService<Loan> {

    public Loan save(Loan loan);
    public Loan getById(int id);
    public void deleteById(int id);

    public List<Loan> getAllByAccountId(int accountId);
    public List<Loan> getAllByUserId(int userId);



}
