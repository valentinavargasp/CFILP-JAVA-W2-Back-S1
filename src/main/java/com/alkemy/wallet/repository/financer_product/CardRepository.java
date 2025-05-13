package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
