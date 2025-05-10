package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
