package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.models.financer_product.TimeDeposit;

import java.util.List;

public interface TimeDepositService extends FinancerProductService<TimeDeposit> {

    //TODO: metodos para el plazo fijo, tiene que debitar de la cuenta el monto a colocar, sumarle el interes y  devolverlo al finalizar el plazo .

    public TimeDeposit getByExpirationDate(String expirationDate);



}
