package com.alkemy.wallet.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.transaction.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
