package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.TransactionDTO;
import com.alkemy.wallet.models.transaction.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.user.username", target = "username")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(target = "account", ignore = true) // Set manually in service
    Transaction toEntity(TransactionDTO dto);
}
