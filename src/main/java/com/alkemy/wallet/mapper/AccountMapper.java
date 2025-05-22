package com.alkemy.wallet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.models.account.Account;


@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountType.accountType", target = "accountType")
    @Mapping(source = "currency", target = "currency")
    AccountDTO toDTO(Account account);

    List<AccountDTO> toDTOList(List<Account> accounts);
}
