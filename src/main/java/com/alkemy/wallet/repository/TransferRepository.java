package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
