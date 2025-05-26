package com.alkemy.wallet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.WithdrawalDTO;
import com.alkemy.wallet.models.transaction.Withdrawal;

@Mapper(componentModel = "spring")
public interface WithdrawalMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "method", target = "method")
    @Mapping(target = "description", ignore = true)
    WithdrawalDTO toDto(Withdrawal withdrawal);

    @Mapping(target = "account", ignore = true) // se setea en el servicio con el repositorio
    @Mapping(target = "transaction", ignore = true)
    @Mapping(source = "method", target = "method")
    Withdrawal toEntity(WithdrawalDTO dto);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "method", target = "method")  
    @Mapping(target = "description", ignore = true)
    List<WithdrawalDTO> toDtoList(List<Withdrawal> withdrawals);
}