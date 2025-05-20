package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.WithdrawalDTO;
import com.alkemy.wallet.models.transaction.Withdrawal;

@Mapper(componentModel = "spring")
public interface WithdrawalMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "method", target = "method")
    WithdrawalDTO toDto(Withdrawal withdrawal);

    @Mapping(target = "account", ignore = true) // se setea en el servicio con el repositorio
    @Mapping(source = "method", target = "method")
    Withdrawal toEntity(WithdrawalDTO dto);
}