package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
