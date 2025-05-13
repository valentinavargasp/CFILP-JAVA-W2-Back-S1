package com.alkemy.wallet.repository.account;

import com.alkemy.wallet.models.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account,Integer>{
}
