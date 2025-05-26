package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.DepositDTO;
import com.alkemy.wallet.models.transaction.Deposit;

@Mapper(componentModel = "spring", uses = TransactionMapper.class)
public interface DepositMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(target = "description", ignore = true)
    DepositDTO toDto(Deposit deposit);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "transaction", ignore = true)
    Deposit toEntity(DepositDTO dto);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(target = "description", ignore = true)
    DepositDTO toDTO(Deposit savedDeposit);
}
