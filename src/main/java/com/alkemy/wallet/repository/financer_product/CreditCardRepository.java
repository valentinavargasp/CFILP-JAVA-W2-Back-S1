package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
