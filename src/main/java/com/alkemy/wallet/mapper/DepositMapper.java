package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.DepositDTO;
import com.alkemy.wallet.models.transaction.Deposit;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "method", target = "method")
    DepositDTO toDto(Deposit deposit);

    @Mapping(target = "account", ignore = true) // se setea manualmente en el servicio
    @Mapping(source = "method", target = "method")
    Deposit toEntity(DepositDTO dto);
}
