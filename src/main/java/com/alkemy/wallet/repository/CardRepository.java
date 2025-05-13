package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
