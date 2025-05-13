package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.DebitCard;

public interface DebitCardRepository extends JpaRepository<DebitCard, Integer> {


}
