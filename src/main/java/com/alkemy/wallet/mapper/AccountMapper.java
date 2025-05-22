package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.models.account.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountType.accountType", target = "accountType")
    AccountDTO toAccountDTO(Account account);

    Account toEntity(AccountDTO accountDTO);

}
