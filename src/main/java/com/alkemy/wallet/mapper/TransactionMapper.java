package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.alkemy.wallet.dto.TransactionDTO;
import com.alkemy.wallet.models.transaction.Transaction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(source = "account.id", target = "accountId")
    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);
}
