package com.alkemy.wallet.repository.account;

import com.alkemy.wallet.models.account.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(int userId);

    Optional<Account> findByCbu(String cbu);

    Optional<Account> findByAlias(String alias);
}
