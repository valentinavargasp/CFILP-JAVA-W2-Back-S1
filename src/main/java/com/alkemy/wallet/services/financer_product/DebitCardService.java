package com.alkemy.wallet.services.financer_product;

import com.alkemy.wallet.exceptions.InsufficientFundsException;
import com.alkemy.wallet.models.financer_product.DebitCard;

import java.math.BigDecimal;

public interface DebitCardService extends CardService<DebitCard> {

    /**
     * Consulta el saldo disponible de la tarjeta de débito
     *
     * @param debitCardId ID de la tarjeta
     * @return el saldo disponible
     */
    double getAvailableBalance(int debitCardId);

    /**
     * Realiza un pago con la tarjeta de débito
     * @param debitCardId ID de la tarjeta
     * @param amount monto a pagar
     * @throws InsufficientFundsException si no hay saldo suficiente
     */
    void payWithDebitCard(int debitCardId, BigDecimal amount) throws InsufficientFundsException;



}
