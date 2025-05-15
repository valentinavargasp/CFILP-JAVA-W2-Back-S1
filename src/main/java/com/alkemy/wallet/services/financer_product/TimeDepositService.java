package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.TimeDeposit;

import java.util.List;

public interface TimeDepositService extends FinancerProductService<TimeDeposit> {

    public TimeDeposit save(TimeDeposit timeDeposit);
    public TimeDeposit getById(int id);
    public void deleteById(int id);

    public TimeDeposit getByExpirationDate(String expirationDate);

    public List<TimeDeposit> getAllByAccountId(int accountId);
    public List<TimeDeposit> getAllByUserId(int userId);

}
