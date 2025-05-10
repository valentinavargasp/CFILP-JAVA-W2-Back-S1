package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account,Integer>{
}
