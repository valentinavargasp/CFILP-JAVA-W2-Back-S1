package com.alkemy.wallet.repository.account;

import com.alkemy.wallet.models.account.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
