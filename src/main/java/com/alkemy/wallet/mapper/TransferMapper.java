package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.TransferDTO;
import com.alkemy.wallet.models.transaction.Transfer;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "destinationAccount.id", target = "destinationAccountId")
    @Mapping(target = "description", ignore = true)
    TransferDTO toDto(Transfer transfer);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "destinationAccount", ignore = true)
    Transfer toEntity(TransferDTO dto);

}
